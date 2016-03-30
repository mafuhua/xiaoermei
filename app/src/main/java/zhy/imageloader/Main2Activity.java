package zhy.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.yuen.xiaoermei.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;

public class Main2Activity extends Activity {
    private GridView gridView;
    private Button button;
    private ArrayList<String> mySelectedImage;
    private ImageOptions options;

    private void assignViews() {
        gridView = (GridView) findViewById(R.id.gridView);
        button = (Button) findViewById(R.id.button);
       /* if (mySelectedImage != null) {

            gridView.setAdapter(new MyAdapter());
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_selector_image_result);

        assignViews();
        options = new ImageOptions.Builder()
                // 是否忽略GIF格式的图片
                .setIgnoreGif(true)
                        // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        // 下载中显示的图片
                .setLoadingDrawableId(R.drawable.ic_launcher)
                        // 下载失败显示的图片
                .setFailureDrawableId(R.drawable.ic_launcher)
                        // 得到ImageOptions对象
                .build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, SelectorImageActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (resultCode == 100) {
            mySelectedImage = data.getStringArrayListExtra("MySelectedImage");
              Log.d("mafuhua", "7777777777777****" + mySelectedImage.size());
            gridView.setAdapter(new MyAdapter());
        }

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mySelectedImage == null ? 0 : mySelectedImage.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(Main2Activity.this, R.layout.layout_selector_gridview, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String imag = mySelectedImage.get(position);
            if (imag != null) {

                x.image().bind(viewHolder.iditemimage, new File(imag).toURI().toString(), options);
            }
            return convertView;
        }

        public class ViewHolder {
            public final ImageView iditemimage;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                iditemimage = (ImageView) root.findViewById(R.id.id_item_image);
            }
        }
    }
}
