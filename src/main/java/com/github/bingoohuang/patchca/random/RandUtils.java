package com.github.bingoohuang.patchca.random;


import java.security.SecureRandom;
import java.util.Random;

public class RandUtils {
    /*
     * Thread-safe. It uses synchronization to protect the integrity of its state.
     * See SecureRandom.nextBytes with synchronized keyword.
     */
    private static final SecureRandom RANDOM = new SecureRandom();

    public static boolean randBoolean() {
        return RANDOM.nextBoolean();
    }

    public static double randDouble() {
        return RANDOM.nextDouble();
    }

    public static float randFloat() {
        return RANDOM.nextFloat();
    }

    public static int randInt() {
        return RANDOM.nextInt();
    }

    public static int randInt(int n) {
        return RANDOM.nextInt(n);
    }

    public static long randLong() {
        return RANDOM.nextLong();
    }

    public static String randNum(int count) {
        StringBuilder sb = new StringBuilder(count);
        while (sb.length() < count) {
            sb.append(Math.abs(randLong()));
        }

        return sb.replace(count, sb.length(), "").toString();
    }


    private final static char[] CHINESE_LEVELONE = ChineseUtils.levelOne().toCharArray();

    public static String randChinese(int count) {
        return random(count, 0, 0, false, false, CHINESE_LEVELONE, RANDOM);
    }

    public static String rand(int count, String allowedChars) {
        return random(count, 0, 0, false, false, allowedChars.toCharArray(), RANDOM);
    }

    /**
     * <p>Creates a random string based on a variety of options, using
     * supplied source of randomness.</p>
     * <p/>
     * <p>If start and end are both {@code 0}, start and end are set
     * to {@code ' '} and {@code 'z'}, the ASCII printable
     * characters, will be used, unless letters and numbers are both
     * {@code false}, in which case, start and end are set to
     * {@code 0} and {@code Integer.MAX_VALUE}.
     * <p/>
     * <p>If set is not {@code null}, characters between start and
     * end are chosen.</p>
     * <p/>
     * <p>This method accepts a user-supplied {@link Random}
     * instance to use as a source of randomness. By seeding a single
     * {@link Random} instance with a fixed seed and using it for each call,
     * the same random sequence of strings can be generated repeatedly
     * and predictably.</p>
     *
     * @param count   the length of random string to create
     * @param start   the position in set of chars to start at
     * @param end     the position in set of chars to end before
     * @param letters only allow letters?
     * @param numbers only allow numbers?
     * @param chars   the set of chars to choose randoms from, must not be empty.
     *                If {@code null}, then it will use the set of all chars.
     * @param random  a source of randomness.
     * @return the random string
     * @throws ArrayIndexOutOfBoundsException if there are not
     *                                        {@code (end - start) + 1} characters in the set array.
     * @throws IllegalArgumentException       if {@code count} &lt; 0 or the provided chars array is empty.
     * @since 2.0
     */
    public static String random(int count, int start, int end, boolean letters, boolean numbers,
                                char[] chars, Random random) {
        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (chars != null && chars.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        }

        if (start == 0 && end == 0) {
            if (chars != null) {
                end = chars.length;
            } else {
                if (!letters && !numbers) {
                    end = Integer.MAX_VALUE;
                } else {
                    end = 'z' + 1;
                    start = ' ';
                }
            }
        } else {
            if (end <= start) {
                throw new IllegalArgumentException("Parameter end (" + end + ") must be greater than start (" + start + ")");
            }
        }

        char[] buffer = new char[count];
        int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if (letters && Character.isLetter(ch)
                    || numbers && Character.isDigit(ch)
                    || !letters && !numbers) {
                if (ch >= 56320 && ch <= 57343) {
                    if (count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if (ch >= 55296 && ch <= 56191) {
                    if (count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if (ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }
}
