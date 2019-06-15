package com.kisslang.source.parser.ast.helper;

public final class ContextRecognize {

    private final static boolean isSeparator(char c) {

        if (c=='.'){
            return true;
        }

        return false ;
    }

    public final static boolean isNumberLike(final String term) {

        final int lastIndex = term.length() - 1;

        if (!Character.isDigit(term.charAt(0))) {
            return false;
        }

        for (int i = 1; i < lastIndex; i++) {

            if (!Character.isDigit(term.charAt(i))) {

                if (!isSeparator(term.charAt(i))) {
                    return false;
                }
            }
        }

        if (!Character.isDigit(term.charAt(lastIndex))) {
            return false;
        }

        return true;
    }

    public final static boolean isBooleanLike(final String term){

        if(term.equals("True") || term.equals("False")) {
            return true;
        }

        return false;

    }


}
