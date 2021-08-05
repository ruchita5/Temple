package com.example.user.templettd;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;



/**
 * Created by BALKRISHNA on 6/12/2017.
 */

public class AttendanceParentAdapter extends BaseAdapter {
    private Context context;

    private java.util.Calendar month;
    public GregorianCalendar pmonth;
    public GregorianCalendar pmonthmaxset;
    private GregorianCalendar selectedDate;
    int firstDay;
    int maxWeeknumber;
    int maxP;
    int calMaxP;
    int lastWeekDay;
    int leftDays=0;
    int mnthlength;
    String itemvalue, curentDateString;
    DateFormat df;

    private ArrayList<String> items;
    public static List<String> day_string;
    private View previousView;

    public AttendanceParentAdapter(Context context, GregorianCalendar monthCalendar) {

        AttendanceParentAdapter.day_string = new ArrayList<String>();
        Locale.setDefault(Locale.US);
        month = monthCalendar;
        selectedDate = (GregorianCalendar) monthCalendar.clone();
        this.context = context;
        month.set(GregorianCalendar.DAY_OF_MONTH, 1);

        this.items = new ArrayList<String>();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());
        refreshDays();

    }
    public void setItems(ArrayList<String> items) {
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).length() == 1) {
                items.set(i, "0" + items.get(i));
            }
        }
        this.items = items;
    }

    public int getCount() {
        return day_string.size();
    }

    public Object getItem(int position) {
        return day_string.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView dayView;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.cal_item, null);

        }


        dayView = (TextView) v.findViewById(R.id.date);
        String[] separatedTime = day_string.get(position).split("-");


        String gridvalue = separatedTime[2].replaceFirst("^0*", "");
        if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
            dayView.setTextColor(Color.GRAY);
            dayView.setClickable(false);
            dayView.setFocusable(false);
        } else {
            // setting curent month's days in blue color.
            dayView.setTextColor(Color.BLACK);
        }


        /*if (day_string.get(position).equals(curentDateString)) {

            v.setBackgroundColor(Color.CYAN);
        } else {
            v.setBackgroundColor(Color.WHITE);
        }*/


        dayView.setText(gridvalue);

        // create date string for comparison
        String date = day_string.get(position);

        if (date.length() == 1) {
            date = "0" + date;
        }
        String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }

        // show icon if date is not empty and it exists in the items array
        /*ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
        if (date.length() > 0 && items != null && items.contains(date)) {
            iw.setVisibility(View.VISIBLE);
        } else {
            iw.setVisibility(View.GONE);
        }
        */

        setEventView(v, position,dayView);

        return v;
    }


    public void refreshDays() {
        // clear items
        items.clear();
        day_string.clear();
        Locale.setDefault(Locale.US);
        pmonth = (GregorianCalendar) month.clone();
        // month start day. ie; sun, mon, etc
        firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
        // finding number of weeks in current month.
        maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
        // allocating maximum row number for the gridview.
        mnthlength = maxWeeknumber * 7;
        maxP = getMaxP(); // previous month maximum day 31,30....
        calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
        /**
         * Calendar instance for getting a complete gridview including the three
         * month's (previous,current,next) dates.
         */
        pmonthmaxset = (GregorianCalendar) pmonth.clone();
        /**
         * setting the start date as previous month's required date.
         */
        pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

        /**
         * filling calendar gridview.
         */
        for (int n = 0; n < mnthlength; n++) {

            itemvalue = df.format(pmonthmaxset.getTime());
            pmonthmaxset.add(GregorianCalendar.DATE, 1);
            day_string.add(itemvalue);

        }
    }

    private int getMaxP() {
        int maxP;
        if (month.get(GregorianCalendar.MONTH) == month.getActualMinimum(GregorianCalendar.MONTH)) {
            pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            pmonth.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }
        maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

        return maxP;
    }




    public void setEventView(View v, int pos, TextView txt){

        /*int len= AttendanceParentlist.collection.size();
        for (int i = 0; i < len; i++) {
            AttendanceParentlist cal_obj=AttendanceParentlist.collection.get(i);
            //String date=cal_obj.getHsdate();
            *//*String enddate=cal_obj.getHedate();
            String curdate=day_string.get(pos);*//*
            int len1=day_string.size();
            if (len1>pos) {
                String date=cal_obj.getAdate();
                String curdate=day_string.get(pos);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = df.format(c.getTime());
                Date cur_date,start_date,end_date;
                try {
                    cur_date = df.parse(curdate);
                    start_date=df.parse(date);
                    if ((betweenA(cur_date,start_date,AttendanceParentlist.collection.get(i).getStatus()))) {
                        txt.setTextColor(Color.RED);
                        leftDays = leftDays +1;
                    }
                    else if ((betweenP(cur_date,start_date,AttendanceParentlist.collection.get(i).getStatus()))) {
                        txt.setTextColor(Color.GREEN);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        StudentAttendanceCalendarFragment.txt_present.setText("Absent : "+leftDays);
        StudentAttendanceCalendarFragment.txt_pre.setText("Present : "+(len-leftDays));*/
    }
    public static boolean betweenA(Date date, Date dateStart, String status) {
        if (date != null && dateStart != null ) {
            if (date.equals(dateStart)&&status.equalsIgnoreCase("A")){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public static boolean betweenP(Date date, Date dateStart, String status) {
        if (date != null && dateStart != null ) {
            if (date.equals(dateStart)&&status.equalsIgnoreCase("P")){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
