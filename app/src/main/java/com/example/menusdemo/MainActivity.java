package com.example.menusdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_message = (TextView) findViewById(R.id.tv_message);

        //ContextMenu需註冊，OptionsMenu不需要
        //呼叫registerForContextMenu()來指定長按某個元件時會跳出ContextMenu
        registerForContextMenu(tv_message);
    }


    //當OptionsMenu要呈現時，系統會呼叫onCreateOptionsMenu()並傳遞空的menu參數
    //需透過MenuInflater將載入的XML檔案內容交給menu參數
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //1.透過MenuInflater新增選項
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);


        //2.透過menu參數直接呼叫add()動態新增選項
        //menu.add("Yamingshan National Park");
        // menu.add("Taroko National Park");
        //menu.add("My Location");
        //menu.add("Exit");


        //回傳true，OptionsMenu才能顯示在畫面上
        //onCreateOptionsMenu()才有回傳直，onCreateContextMenu()沒有
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String message;
        switch (item.getItemId()) {
            case R.id.yamingshan:
                message = getString(R.string.yamingshan);
                break;
            case R.id.guide:
                message = getString(R.string.yamingshan) + ">" + getString(R.string.guide);
                break;
            case R.id.traffic:
                message = getString(R.string.yamingshan) + ">" + getString(R.string.traffic);
                break;
            case R.id.yushan:
                message = getString(R.string.yushan);
                break;
            case R.id.taroko:
                message = getString(R.string.taroko);
                break;
            case R.id.location:
                message = getString(R.string.myLocation);
                break;
            case R.id.exit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

        tv_message.setText(message);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                tv_message.setText(null);
                break;
            case R.id.yellow:
                tv_message.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.green:
                tv_message.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blue:
                tv_message.setBackgroundColor(Color.BLUE);
                break;
            default:
                return super.onContextItemSelected(item);
        }


        return true;
    }

    //按下某個元件會跳出PopupMenu,記得要呼叫show()
    public void onDelete(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                tv_message.setText(menuItem.getTitle());
                return true;
            }
        });
    }


}