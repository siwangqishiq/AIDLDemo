package com.xinlan.aidldemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xinlan.aidldemo.localservice.LocalService;

/**
 * AIDL通信规则 接口定义语言 用于约束两个应用之间的通信规则
 * 
 * @author panyi
 *
 */
public class MainActivity extends Activity {
	private Button queryBtn;
	private TextView nameText;
	private EditText numberText;

	private Connection mConnection = new Connection();
	private IStudentQuery queryService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent it = new Intent(this, LocalService.class);
		bindService(it, mConnection, Context.BIND_AUTO_CREATE);

		queryBtn = (Button) findViewById(R.id.query);
		nameText = (TextView) findViewById(R.id.name);
		numberText = (EditText) findViewById(R.id.content);

		queryBtn.setOnClickListener(new QueryButtonClick());
	}

	private final class QueryButtonClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			String number = numberText.getText().toString().trim();
			System.out.println("number--->" + number);
			if (queryService != null) {
				nameText.setText(queryService.queryById(number));
			} else {
				nameText.setText("查询服务终止...");
			}// end if
		}
	}// end inner class

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mConnection);
	}

	private final class Connection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("ComponentName--->" + name);

			queryService = (IStudentQuery) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("ComponentName--->" + name);
			queryService = null;
		}
	}// end inner class
}// end class
