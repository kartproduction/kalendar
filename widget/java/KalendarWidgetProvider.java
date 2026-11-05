package com.kasign.kalendar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import java.util.Calendar;

/** Home-screen widget: today's BS and AD date, plus the next festival. */
public class KalendarWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] ids) {
        for (int id : ids) manager.updateAppWidget(id, build(context));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String a = intent.getAction();
        if (Intent.ACTION_DATE_CHANGED.equals(a) || Intent.ACTION_TIME_CHANGED.equals(a) || Intent.ACTION_TIMEZONE_CHANGED.equals(a)) {
            AppWidgetManager m = AppWidgetManager.getInstance(context);
            int[] ids = m.getAppWidgetIds(new ComponentName(context, KalendarWidgetProvider.class));
            if (ids.length > 0) onUpdate(context, m, ids);
        }
    }

    private static RemoteViews build(Context c) {
        RemoteViews v = new RemoteViews(c.getPackageName(), R.layout.widget_kalendar);
        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR), m = now.get(Calendar.MONTH) + 1, d = now.get(Calendar.DAY_OF_MONTH);
        long today = KalendarDates.epochDay(y, m, d);
        int wd = KalendarDates.weekday(today);
        int[] bs = KalendarDates.toBs(today);

        v.setTextViewText(R.id.kw_weekday, KalendarDates.WEEK_NE[wd] + " · " + KalendarDates.WEEK_EN[wd]);
        if (bs != null) {
            v.setTextViewText(R.id.kw_bs_day, KalendarDates.nepaliDigits(bs[2]));
            v.setTextViewText(R.id.kw_bs_month, KalendarDates.BS_MONTHS[bs[1]] + " " + KalendarDates.nepaliDigits(bs[0]));
        } else {
            v.setTextViewText(R.id.kw_bs_day, String.valueOf(d));
            v.setTextViewText(R.id.kw_bs_month, "");
        }
        v.setTextViewText(R.id.kw_ad, d + " " + KalendarDates.MONTH_EN[m - 1] + " " + y);

        String[] f = KalendarDates.nextFestival(today, y, m, d);
        if (f != null) {
            long days = Long.parseLong(f[2]);
            String when = days <= 0 ? "आज" : days == 1 ? "भोलि" : KalendarDates.nepaliDigits(days) + " दिनमा";
            v.setTextViewText(R.id.kw_next, "★ " + f[1] + " · " + when);
        } else {
            v.setTextViewText(R.id.kw_next, "");
        }

        Intent open = c.getPackageManager().getLaunchIntentForPackage(c.getPackageName());
        if (open != null) {
            int flags = PendingIntent.FLAG_UPDATE_CURRENT | (Build.VERSION.SDK_INT >= 23 ? PendingIntent.FLAG_IMMUTABLE : 0);
            v.setOnClickPendingIntent(R.id.kw_root, PendingIntent.getActivity(c, 0, open, flags));
        }
        return v;
    }
}
