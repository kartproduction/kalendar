package com.kasign.kalendar;

/** Plain-Java date maths for the widget (no Android classes, works on every API level). */
final class KalendarDates {
    // Baisakh 1, 2083 BS = 14 April 2026 (same anchor as www/index.html)
    private static final int ANCHOR_BS_YEAR = 2083;

    static final String[] BS_MONTHS = {"बैशाख", "जेठ", "असार", "श्रावण", "भदौ", "असोज", "कात्तिक", "मंसिर", "पौष", "माघ", "फागुन", "चैत"};
    static final String[] WEEK_NE = {"आइतबार", "सोमबार", "मंगलबार", "बुधबार", "बिहीबार", "शुक्रबार", "शनिबार"};
    static final String[] WEEK_EN = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    static final String[] MONTH_EN = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    /** Days since 1970-01-01 for a Gregorian date (month 1-12). */
    static long epochDay(int y, int m, int d) {
        y -= m <= 2 ? 1 : 0;
        long era = (y >= 0 ? y : y - 399) / 400;
        long yoe = y - era * 400;
        long doy = (153L * (m + (m > 2 ? -3 : 9)) + 2) / 5 + d - 1;
        long doe = yoe * 365 + yoe / 4 - yoe / 100 + doy;
        return era * 146097 + doe - 719468;
    }

    /** 0 = Sunday ... 6 = Saturday. */
    static int weekday(long epochDay) {
        return (int) Math.floorMod(epochDay + 4, 7L);
    }

    private static int len(int year, int month) {
        return BsData.MONTH_DAYS[year - BsData.FIRST_YEAR][month];
    }

    /** Returns {year, month0, day} in BS, or null when outside the table. */
    static int[] toBs(long epochDay) {
        long off = epochDay - epochDay(2026, 4, 14);
        int year = ANCHOR_BS_YEAR, month = 0;
        int lastYear = BsData.FIRST_YEAR + BsData.MONTH_DAYS.length - 1;
        while (off < 0) {
            month--;
            if (month < 0) { month = 11; year--; }
            if (year < BsData.FIRST_YEAR) return null;
            off += len(year, month);
        }
        while (true) {
            if (year > lastYear) return null;
            int l = len(year, month);
            if (off < l) break;
            off -= l;
            month++;
            if (month > 11) { month = 0; year++; }
        }
        return new int[]{year, month, (int) off + 1};
    }

    static String nepaliDigits(long n) {
        StringBuilder sb = new StringBuilder();
        for (char c : String.valueOf(n).toCharArray()) sb.append(c >= '0' && c <= '9' ? (char) ('\u0966' + (c - '0')) : c);
        return sb.toString();
    }

    /** Next festival starting today or later: {English, Nepali, daysAway} or null. */
    static String[] nextFestival(long today, int y, int m, int d) {
        String key = String.format(java.util.Locale.US, "%04d-%02d-%02d", y, m, d);
        for (String[] f : BsData.FEST) {
            if (f[0].compareTo(key) >= 0) {
                int fy = Integer.parseInt(f[0].substring(0, 4)), fm = Integer.parseInt(f[0].substring(5, 7)), fd = Integer.parseInt(f[0].substring(8, 10));
                return new String[]{f[2], f[3], String.valueOf(epochDay(fy, fm, fd) - today)};
            }
        }
        return null;
    }

    private KalendarDates() {}
}
