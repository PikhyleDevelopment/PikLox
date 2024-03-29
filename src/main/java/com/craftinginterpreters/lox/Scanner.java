package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.craftinginterpreters.lox.TokenType.*;

/**
 * The type Scanner.
 */
class Scanner {
    /**
     * The constant keywords.
     */
    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and", AND);
        keywords.put("class", CLASS);
        keywords.put("else", ELSE);
        keywords.put("false", FALSE);
        keywords.put("for", FOR);
        keywords.put("fun", FUN);
        keywords.put("if", IF);
        keywords.put("nil", NIL);
        keywords.put("or", OR);
        keywords.put("return", RETURN);
        keywords.put("super", SUPER);
        keywords.put("this", THIS);
        keywords.put("true", TRUE);
        keywords.put("var", VAR);
        keywords.put("while", WHILE);
    }

    /**
     * The Source.
     */
    private final String source;
    /**
     * The Tokens.
     */
    private final List<Token> tokens = new ArrayList<>();
    /**
     * The Start.
     */
    private int start = 0;
    /**
     * The Current.
     */
    private int current = 0;
    /**
     * The Line.
     */
    private int line = 1;

    /**
     * Instantiates a new Scanner.
     *
     * @param source the source
     */
    Scanner(String source) {
        this.source = source;
    }

    /**
     * Scan tokens list.
     *
     * @return the list
     */
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    /**
     * Scan token.
     */
    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(' -> addToken(LEFT_PAREN);
            case ')' -> addToken(RIGHT_PAREN);
            case '{' -> addToken(LEFT_BRACE);
            case '}' -> addToken(RIGHT_BRACE);
            case ',' -> addToken(COMMA);
            case '.' -> addToken(DOT);
            case '-' -> addToken(MINUS);
            case '+' -> addToken(PLUS);
            case ';' -> addToken(SEMICOLON);
            case '*' -> addToken(STAR);
            case '!' -> addToken(match('=') ? BANG_EQUAL : BANG);
            case '=' -> addToken(match('=') ? EQUAL_EQUAL : EQUAL);
            case '<' -> addToken(match('=') ? LESS_EQUAL : LESS);
            case '>' -> addToken(match('=') ? GREATER_EQUAL : GREATER);
            case '/' -> {
                if (match('/')) {
                    // A comment goes until the end of the line.
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else if (match('*')) {
                    // A comment goes until we hit a * followed by /
                    while (peek() != '*' && peekNext() != '/' && !isAtEnd()) {
                        advance();
                    }
                    advance();
                    advance();
                } else {
                    addToken(SLASH);
                }
            }
            case ' ', '\r', '\t' -> {
                // Ignore white space.
            }
            case '\n' -> line++;
            case '"' -> string();


            default -> {
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    Lox.error(line, "Unexpected character.");
                }
            }
        }
    }

    /**
     * Identifier.
     */
    private void identifier() {
        while (isAlphaNumeric(peek())) advance();

        String text = source.substring(start, current);
        TokenType type = keywords.get(text);
        if (type == null) type = IDENTIFIER;
        addToken(type);
    }

    /**
     * Number.
     */
    private void number() {
        while (isDigit(peek())) advance();

        // Look for a fractional part.
        if (peek() == '.' && isDigit(peekNext())) {
            // Consume the "."
            advance();

            while (isDigit(peek())) advance();
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    /**
     * String.
     */
    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            Lox.error(line, "Unterminated string.");
            return;
        }

        // The closing "
        advance();

        // Trim the surrounding quotes
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    /**
     * Like a conditional advance().
     * Only consume the current character if it's what we
     * are looking for.
     *
     * @param expected The expected next character.
     * @return Returns true, unless we are at the end of the file.
     */
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    /**
     * Is at end boolean.
     *
     * @return the boolean
     */
    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Like advance(), but doesn't actually consume the character.
     *
     * @return The next upcoming character
     */
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    /**
     * Peek next char.
     *
     * @return the char
     */
    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    /**
     * Is alpha boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    /**
     * Is alpha numeric boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    /**
     * Is digit boolean.
     *
     * @param c the c
     * @return the boolean
     */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * Consumes the next character in the source file
     *
     * @return The consumed next character in the source file.
     */
    private char advance() {
        return source.charAt(current++);
    }

    /**
     * Grabs the text of the current lexeme and creates a
     * new token for it.
     *
     * @param type @see TokenType
     */
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    /**
     * An overload for literals.
     *
     * @param type    @see TokenType
     * @param literal A token with a literal value.
     */
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}
