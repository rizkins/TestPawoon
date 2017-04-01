package rinus.pawoon;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import db.DBTes;
import models.TesDTO;
import utils.Contents;

import static android.R.attr.name;

public class TodoList extends AppCompatActivity {

    ListView Items;

    private OrderAdapter m_adapter;
    private ArrayList<TesDTO> m_orders = null;
    private DBTes _tesDB;
    private TesDTO _tes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Items = (ListView)findViewById(R.id.list_item);

        _tesDB=new DBTes(this, Contents.getDBHelper().getDB());
        m_orders=_tesDB.selectAll();
        this.m_adapter = new OrderAdapter(this, R.layout.row_tes, m_orders);
        Items.setAdapter(this.m_adapter);

    }



    private class OrderMenu{

        private String title;
        private String completed;
        private String userId;
        private String id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCompleted() {
            return completed;
        }

        public void setCompleted(String completed) {
            this.completed = completed;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    private class OrderAdapter extends ArrayAdapter<TesDTO> {

        private ArrayList<TesDTO> items;

        public OrderAdapter(Context context, int textViewResourceId, ArrayList<TesDTO> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.row_tes, null);
            }

            TesDTO o = items.get(position);
            if (o != null) {
                TextView Title = (TextView) v.findViewById(R.id.title);
                CheckBox CheckB = (CheckBox) v.findViewById(R.id.check_row);
                Title.setText(o.getTitle());
                if(o.getCompleted().equalsIgnoreCase("true")){
                    CheckB.setChecked(true);
                }
                else{
                    CheckB.setChecked(false);
                }
            }
            return v;
        }
    }
}
