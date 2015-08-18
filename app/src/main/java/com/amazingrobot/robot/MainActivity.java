package com.amazingrobot.robot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {

    private ChatListMgr articleListMgr;
    private ListView listView1;
    private TextView textView;
    private EditText editText;
    private String sendStr;
    private ArrayList<ChatEntity> list = new ArrayList<ChatEntity>();
    private ChatAdapter adapter;
    /**
     * 消息处理机制
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    editText.setText("");
                    listView1.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.botton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        initView();
        articleListMgr = new ChatListMgr(MainActivity.this, handler, list);
        adapter = new ChatAdapter(list, MainActivity.this);

    }

    private void initView() {
        listView1 = (ListView) findViewById(R.id.listView1);
        textView = (TextView) findViewById(R.id.send);
        editText = (EditText) findViewById(R.id.edit);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramView) {
                sendStr = editText.getText().toString();
                if (!"".equals(sendStr)) {
                    list.add(new ChatEntity(ChatEntity.Type.OUTCOMING, new Date(), sendStr));
                    articleListMgr.httpGet(sendStr);
                }
            }
        });

    }

    class ChatAdapter extends BaseAdapter {
        private ArrayList<ChatEntity> chat_list;
        private LayoutInflater inflater;
        private Context context;
        private ViewHodel viewHodel;
        private ViewHolder2 holder2;

        public ChatAdapter(ArrayList<ChatEntity> chat_list, Context context) {
            super();
            this.chat_list = chat_list;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return chat_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return chat_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            ChatEntity chatEntity = chat_list.get(position);

            if (chatEntity.getType() == ChatEntity.Type.OUTCOMING) {
                return 0;
            }
            return 1;
        }

        @Override
        public int getViewTypeCount() {
            // TODO Auto-generated method stub
            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                if (getItemViewType(position) == 0) {
                    viewHodel = new ViewHodel();
                    convertView = inflater.inflate(R.layout.item_wo, parent,
                            false);
                    viewHodel.mDate = (TextView) convertView
                            .findViewById(R.id.me_title_time);
                    viewHodel.mMsg = (TextView) convertView
                            .findViewById(R.id.me_msg);
                    convertView.setTag(viewHodel);
                } else {
                    holder2 = new ViewHolder2();
                    convertView = inflater.inflate(R.layout.item_tuling,
                            parent, false);

                    holder2.mDate = (TextView) convertView
                            .findViewById(R.id.me_title_time);
                    holder2.mMsg = (TextView) convertView
                            .findViewById(R.id.me_msg);

                    convertView.setTag(holder2);
                }

            } else {
                if (getItemViewType(position) == 0) {
                    viewHodel = (ViewHodel) convertView.getTag();
                } else if(getItemViewType(position) == 1){
                    holder2 = (ViewHolder2) convertView.getTag();
                }


            }

            ChatEntity chatEntity = list.get(position);
            if (getItemViewType(position) == 0) {
                viewHodel.mDate.setText(new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss").format(chatEntity.getDate()));
                viewHodel.mMsg.setText(chatEntity.getMsg());

                //holder2.mMsg.setText(chatEntity.getStart());

            } else if(getItemViewType(position) == 1){
                holder2.mDate.setText(new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss").format(chatEntity.getDate()));
                holder2.mMsg.setText(chatEntity.getMsg());
               // holder2.mMsg.setText(chatEntity.getStart());
            }

            return convertView;
        }

        class ViewHodel {
            TextView mDate;
            TextView mMsg;
        }

        class ViewHolder2 {
            TextView mDate;
            TextView mMsg;
        }
    }
}
