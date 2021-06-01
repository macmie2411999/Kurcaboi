package com.example.nreaderv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity3 extends AppCompatActivity {

    // khai báo biến
    ListView listView;
    CustomAdapter customAdapter;
    ArrayAdapter adapter;
    ArrayList<StructureOfElement> arrayStructureOfElement;
    String linkRSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // mapping
        listView = (ListView) findViewById(R.id.listTitle);

        // tạo một array để chứa các phần tử
        arrayStructureOfElement = new ArrayList<StructureOfElement>();

        // tạo một đối tượng ArrayAdapter, liên kết listView với arrayStructureOfElement thông qua adapter này
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayStructureOfElement);
        listView.setAdapter(adapter);

        // phương thức để nhận link RSS của chủ đề bài báo (từ MainActivity2)
        setLink();

        // khời chạy tiến trình trên MainActivity3
        new ReadRSS().execute(linkRSS);

        // đặt sự kiện khi nhấn vào từng phần từ (bài viết) của listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);

                // trong danh sách các bài báo, lấy link của bài báo người dùng chọn rồi chạy nó trên MainActivity4
                intent.putExtra("linkNews", arrayStructureOfElement.get(position).link);
                startActivity(intent);
            }
        });
    }

    // AsyncTask để xử lý đa luồng
    private class ReadRSS extends AsyncTask<String, Void, String> {

        // Phương thức này giúp ghi code HTML từ link RSS vào một String rồi trả String đó về
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedInputException | MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        // phương thức này nhận vào một chuỗi code HTML, lọc lấy các giá trị tên - title, link - dường dẫn, và giá trị của ảnh theo TagName
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();

            // phương thức do người dùng tạo
            Document document = parser.getDocument(s);

            // tạo các node (do item/discription chứa nhiều thuộc tính title, link...)
            NodeList nodeListTitle = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");

            String title = "";
            String link = "";
            String image = "";
            for (int i = 0; i < nodeListTitle.getLength(); i++) {

                // từ các node của item, lọc các giá trị của title và link
                Element element = (Element) nodeListTitle.item(i);

                // phương thức do người dùng tạo
                title = parser.getValue(element, "title");
                link = parser.getValue(element, "link");

                // từ cóc node của description, lọc lấy ảnh, sử dụng regex
                String cdata = nodeListDescription.item(i + 1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if (matcher.find()) {
                    image = matcher.group(1);
                }

                // tạo một element (chứa tên, đường dẫn, ảnh của 1 bài báo), thêm element đó vào arrayStructureOfElement
                arrayStructureOfElement.add(new StructureOfElement(title, link, image));
            }

            // tạo một customAdapter dùng để gắn danh sách các phần tử của arrayStructureOfElement vào listView
            customAdapter = new CustomAdapter(MainActivity3.this, android.R.layout.simple_list_item_1, arrayStructureOfElement);
            listView.setAdapter(customAdapter);
        }
    }

    // // phương thức để nhận link RSS của chủ đề bài báo (từ MainActivity2)
    public void setLink(){
        linkRSS = "";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            linkRSS = bundle.getString("linkRSS", "");
        }
    }
}
