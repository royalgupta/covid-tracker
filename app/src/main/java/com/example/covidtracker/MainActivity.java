package com.example.covidtracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    List<responsemodel>List;
    TextView mtotalcase,mactivecase,mrecovercase,mdeath,date;
    PieChart piechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List =new ArrayList<responsemodel>();
        init();

      apiutil.getApiInterface().countrydata().enqueue(new Callback<java.util.List<responsemodel>>() {
          @RequiresApi(api = Build.VERSION_CODES.N)
          @Override
          public void onResponse(Call<java.util.List<responsemodel>> call, Response<java.util.List<responsemodel>> response) {
              List.addAll(response.body());
              for(int i=0;i<List.size();i++)
              {
                  if(List.get(i).getCountry().equals("India"))
                  {
                      int total=Integer.parseInt(List.get(i).getCases());
                      int active=Integer.parseInt(List.get(i).getActive());
                      int recover=Integer.parseInt(List.get(i).getRecovered());
                      int Death=Integer.parseInt(List.get(i).getDeaths());
                      Log.i("res", String.valueOf(Death));
                      mtotalcase.setText(String.valueOf(total));
                      mactivecase.setText(String.valueOf(active));
                      mrecovercase.setText(String.valueOf(recover));
                      mdeath.setText(String.valueOf(Death));
                        piechart.addPieSlice(new PieModel("total",total,getResources().getColor(R.color.Yellow)));
                      piechart.addPieSlice(new PieModel("recover",recover,getResources().getColor(R.color.green)));
                      piechart.addPieSlice(new PieModel("Death",Death,getResources().getColor(R.color.red)));
                      settext(List.get(i).getUpdated());
                  }
              }
          }

          @Override
          public void onFailure(Call<java.util.List<responsemodel>> call, Throwable t) {

          }
      });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void settext(String update)
    {
        DateFormat dateFormat= new SimpleDateFormat("MMM dd,YYYY");
        long milliseconds=Long.parseLong(update);
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        date.setText("updated at"+dateFormat.format(calendar.getTime()));
    }
    public void init()
    {
        mtotalcase=(TextView)findViewById(R.id.totalcase);
        mactivecase=(TextView)findViewById(R.id.activecase);
        mrecovercase=(TextView)findViewById(R.id.recovercase);
        mdeath=(TextView)findViewById(R.id.death);
        piechart=(PieChart) findViewById(R.id.piechart);
        date=(TextView) findViewById(R.id.date);

    }
}