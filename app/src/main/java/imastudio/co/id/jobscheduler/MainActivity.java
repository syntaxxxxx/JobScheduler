package imastudio.co.id.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    private int jobId = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_start, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startJob();
                break;
            case R.id.btn_cancel:
                cancelJob();
                break;
        }
    }

    //TODO 6
    private void startJob() {
        ComponentName mServiceComponent = new ComponentName(this, GetWeather.class);
        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setRequiresDeviceIdle(false);
        builder.setRequiresCharging(false);
        //builder.setPeriodic(18000);
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
        Toast.makeText(this, "Job Service Started", Toast.LENGTH_SHORT).show();
    }

    private void cancelJob() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(jobId);
        Toast.makeText(this, "Job Service Canceled", Toast.LENGTH_SHORT).show();
        finish();
    }
}
