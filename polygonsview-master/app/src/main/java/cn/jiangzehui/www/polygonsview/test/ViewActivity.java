package cn.jiangzehui.www.polygonsview.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import cn.jiangzehui.www.polygonsview.R;

/**
 * Created by arthur on 2017/6/11.
 */

public class ViewActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private PolygonsviewTest pt;
    private SeekBar sb1, sb2, sb3, sb4, sb5, sb6, sb7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        pt = (PolygonsviewTest) findViewById(R.id.mv);
        sb1 = (SeekBar) findViewById(R.id.sb1);
        sb2 = (SeekBar) findViewById(R.id.sb2);
        sb3 = (SeekBar) findViewById(R.id.sb3);
        sb4 = (SeekBar) findViewById(R.id.sb4);
        sb5 = (SeekBar) findViewById(R.id.sb5);
        sb6 = (SeekBar) findViewById(R.id.sb6);
        sb7 = (SeekBar) findViewById(R.id.sb7);
        sb1.setOnSeekBarChangeListener(this);
        sb2.setOnSeekBarChangeListener(this);
        sb3.setOnSeekBarChangeListener(this);
        sb4.setOnSeekBarChangeListener(this);
        sb5.setOnSeekBarChangeListener(this);
        sb6.setOnSeekBarChangeListener(this);
        sb7.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float values = (float) (seekBar.getProgress()/10.0);
        switch (seekBar.getId()){
            case R.id.sb1:
                pt.setValue1(values);
                break;
            case R.id.sb2:
                pt.setValue2(values);
                break;
            case R.id.sb3:
                pt.setValue3(values);
                break;
            case R.id.sb4:
                pt.setValue4(values);
                break;
            case R.id.sb5:
                pt.setValue5(values);
                break;
            case R.id.sb6:
                pt.setValue6(values);
                break;
            case R.id.sb7:
                pt.setValue7(values);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
