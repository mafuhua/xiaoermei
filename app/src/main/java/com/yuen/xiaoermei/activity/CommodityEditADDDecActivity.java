package com.yuen.xiaoermei.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.CommodityAddImagBean;
import com.yuen.xiaoermei.bean.ShopBrandBean;
import com.yuen.xiaoermei.bean.ShopTypeBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zhy.imageloader.SelectorImageActivity;

/**
 * 商品详情编辑
 */
public class CommodityEditADDDecActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] ShopBrand;
    private String[] ShopType;
    private EditText mEtProductName;
    private Spinner spinner0;
    private EditText mEtProductPrice;
    private EditText mEtProductActivePrice;
    private EditText mEtProductInventory;
    private EditText mEtProductWeight;
    private EditText mEtProductAddress;
    private EditText mEtProductSize;
    private EditText mEtProductColor;
    private EditText mEtProductVolume;
    private EditText mEtProductDec;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private ImageView mIvBtnProductTiJiao;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private ArrayList<String> mySelectedImage;
    private ImageOptions options;
    private GridView mGvSelectorImage;
    private ImageView mIvBtnAddSelectorImage;
    private List<String> ShopTypeFirstIDList1;
    private List<String> ShopTypeFirstIDList2;
    private List<String> ShopTypeFirstIDList3;
    private List<String> ShopBrandList;
    private List<String> ShopBrandIDList;
    private int type;
    private CheckBox mCbCommodityUp;
    private CheckBox mCbCommodityDown;
    private List<String> ImageList = new ArrayList<>();
    private File destDir;
    private EditText mEtProductTaste;
    private String pro_shelves = "0";
    private String pro_brand;
    private String type_id1;
    private String type_id2;
    private String type_id;
    private String resultid;
    private ProgressDialog mypDialog;

    private void assignViews() {
        context = this;
        /**
         * 创建文件夹存放压缩文件
         */
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        destDir = new File(externalStorageDirectory + "/imagcacahe/");
        Log.d("mafuhua", "externalStorageDirectory:" + destDir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mCbCommodityUp = (CheckBox) findViewById(R.id.cb_commodity_up);
        mCbCommodityDown = (CheckBox) findViewById(R.id.cb_commodity_down);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mIvBtnProductTiJiao = (ImageView) findViewById(R.id.iv_btn_product_tijiao);
        mEtProductName = (EditText) findViewById(R.id.et_product_name);
        mEtProductPrice = (EditText) findViewById(R.id.et_product_price);
        mEtProductActivePrice = (EditText) findViewById(R.id.et_product_active_price);
        mEtProductInventory = (EditText) findViewById(R.id.et_product_inventory);
        mEtProductTaste = (EditText) findViewById(R.id.et_product_taste);
        mEtProductWeight = (EditText) findViewById(R.id.et_product_weight);
        mEtProductAddress = (EditText) findViewById(R.id.et_product_address);
        mEtProductSize = (EditText) findViewById(R.id.et_product_size);
        mEtProductColor = (EditText) findViewById(R.id.et_product_color);
        mEtProductVolume = (EditText) findViewById(R.id.et_product_volume);
        mEtProductDec = (EditText) findViewById(R.id.et_product_dec);
        mGvSelectorImage = (GridView) findViewById(R.id.gv_selector_image);
        mIvBtnAddSelectorImage = (ImageView) findViewById(R.id.iv_btn_add_selector_image);
        mTvTitleDec.setText("商品详情");
        mIvBtnAdd.setVisibility(View.GONE);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnAddSelectorImage.setOnClickListener(this);
        mCbCommodityUp.setOnClickListener(this);
        mCbCommodityDown.setOnClickListener(this);
        mIvBtnProductTiJiao.setOnClickListener(this);
        spinner0 = (Spinner) findViewById(R.id.spinner0);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner2.setVisibility(View.GONE);
        spinner3.setVisibility(View.GONE);
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ShopBrandList.get(pos - 1) + "你点击的是:" + pos);
                pro_brand = ShopBrandIDList.get(pos - 1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList1.get(pos - 1) + "你点击的是:" + pos);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList1.get(pos - 1), 1);
                spinner2.setVisibility(View.VISIBLE);
                spinner3.setVisibility(View.GONE);
                type_id1 = ShopTypeFirstIDList1.get(pos - 1);
                type_id = type_id1;
                Log.d("mafuhua", "你点击的是:" + type_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList2.get(pos - 1) + "你点击的是:" + pos);
                spinner3.setVisibility(View.VISIBLE);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList2.get(pos - 1), 2);
                type_id = "";
                type_id2 = type_id1+","+ShopTypeFirstIDList2.get(pos - 1);
                type_id = type_id2;
                Log.d("mafuhua",  "你点击的是:" + type_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList3.get(pos - 1) + "你点击的是:" + pos);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList3.get(pos - 1), 3);
                type_id = type_id2+","+ShopTypeFirstIDList3.get(pos - 1);
                Log.d("mafuhua",  "你点击的是:" + type_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        options = new ImageOptions.Builder()
                // 是否忽略GIF格式的图片
                .setIgnoreGif(true)
                        // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        // 下载中显示的图片
                        // .setLoadingDrawableId(R.drawable.ic_launcher)
                        // 下载失败显示的图片
                        //.setFailureDrawableId(R.drawable.ic_launcher)
                        // 得到ImageOptions对象
                .build();

    }

    private void setSpinnerContent(final Spinner spinners, final String[] mCountries) {
        if (mCountries == null) {
            return;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, mCountries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinners.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_edit_dec);
        SysExitUtil.activityList.add(this);
        assignViews();
        getSHOP_BRAND();
        getSHOP_TYPE(ContactURL.SHOP_TYPE + "0", 0);
    }

    private void getSHOP_BRAND() {
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(ContactURL.SHOP_BRAND);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopBrandBean shopBrandBean = gson.fromJson(res, ShopBrandBean.class);
                List<ShopBrandBean.DataBean> data = shopBrandBean.getData();
                ShopBrand = new String[data.size() + 1];
                ShopBrand[0] = "请选择";
                ShopBrandList = new ArrayList<String>();
                ShopBrandIDList = new ArrayList<String>();
                for (int i = 1; i < data.size() + 1; i++) {
                    ShopBrand[i] = data.get(i - 1).getBrand();
                    ShopBrandList.add(i - 1, data.get(i - 1).getBrand());
                    ShopBrandIDList.add(i - 1, data.get(i - 1).getId());
                }
                setSpinnerContent(spinner0, ShopBrand);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void getSHOP_TYPE(String url, final int spinnerpos) {
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopTypeBean shopTypeBean = gson.fromJson(res, ShopTypeBean.class);
                List<ShopTypeBean.DataBean> data = shopTypeBean.getData();

                if (spinnerpos == 0) {
                    ShopTypeFirstIDList1 = new ArrayList<String>();
                } else if (spinnerpos == 1) {
                    ShopTypeFirstIDList2 = new ArrayList<String>();
                } else if (spinnerpos == 2) {
                    ShopTypeFirstIDList3 = new ArrayList<String>();
                }
                ShopType = new String[data.size() + 1];
                ShopType[0] = "请选择";
                for (int i = 1; i <= data.size(); i++) {
                    ShopType[i] = data.get(i - 1).getNav_name();

                    if (spinnerpos == 0) {
                        ShopTypeFirstIDList1.add(i - 1, data.get(i - 1).getId());

                    } else if (spinnerpos == 1) {
                        ShopTypeFirstIDList2.add(i - 1, data.get(i - 1).getId());

                    } else if (spinnerpos == 2) {
                        ShopTypeFirstIDList3.add(i - 1, data.get(i - 1).getId());
                    }

                }
                if (spinnerpos == 0) {

                    setSpinnerContent(spinner1, ShopType);
                } else if (spinnerpos == 1) {
                    setSpinnerContent(spinner2, ShopType);
                } else if (spinnerpos == 2) {
                    setSpinnerContent(spinner3, ShopType);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

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
            for (int i = 0; i < mySelectedImage.size(); i++) {
                ImageList.add(destDir.toString() + "/" + i + ".jpg");
                Log.d("mafuhua", destDir.toString() + "/" + i + ".jpg");
                Compresspic(ImageList.get(i), mySelectedImage.get(i));
            }

            Log.d("mafuhua", "7777777777777****" + mySelectedImage.size());
            mGvSelectorImage.setAdapter(new MyAdapter());
        }

    }

    public void sendComPic() {
        for (int i = 0; i < mySelectedImage.size(); i++) {

            sendimg(ImageList.get(i));
        }
    }

    private void sendimg(String path) {

        AsyncHttpClient client = new AsyncHttpClient();

        String url = ContactURL.BASE_URL+"/shop/add_img";

        com.loopj.android.http.RequestParams rp = new com.loopj.android.http.RequestParams();

        File file = new File(path);
        Log.d("mafuhua", path + "**************");
        try {
            rp.put("img", file);
            rp.add("id", resultid);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        client.post(url, rp, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.d("mafuhua", "responseBody"+response);
                Gson gson = new Gson();
                CommodityAddImagBean commodityAddImagBean = gson.fromJson(response, CommodityAddImagBean.class);
                int status = commodityAddImagBean.getStatus();
                    mypDialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }


        });
    }

    public void Compresspic(final String path, final String old) {
        new Thread(new Runnable() {//开启多线程进行压缩处理
            private int options;

            @Override
            public void run() {
                // TODO Auto-generated method stub
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(old, opts);
                Log.d("mafuhua", "bitmap.getByteCount():" + bitmap.getByteCount() / 1024);
                options = 80;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                //质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)
                while (baos.toByteArray().length / 1024 > 200) {//循环判断如果压缩后图片是否大于60kb,大于继续压缩
                    baos.reset();//重置baos即让下一次的写入覆盖之前的内容
                    options -= 10;//图片质量每次减少10
                    if (options < 0) options = 0;//如果图片质量小于10，则将图片的质量压缩到最小值
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
                    if (options == 0) break;//如果图片的质量已降到最低则，不再进行压缩
                }
                try {
                    FileOutputStream fos = new FileOutputStream(new File(path));//将压缩后的图片保存的本地上指定路径中
                    fos.write(baos.toByteArray());
                    fos.flush();
                    fos.close();

                    Log.e("图爱散股", path);
                    File file = new File(path);// path为压缩后的图片路径，将这个新生成的file申明为成员变量，后续会把这个file对象上传服务端，后端自动识别
                    Log.d("mafuhua", "file.length()/1024:" + (file.length() / 1024));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
            case R.id.cb_commodity_up:
                mCbCommodityUp.setChecked(true);
                mCbCommodityDown.setChecked(false);
                pro_shelves = "0";
                Toast.makeText(context, "上架", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cb_commodity_down:
                mCbCommodityUp.setChecked(false);
                mCbCommodityDown.setChecked(true);
                Toast.makeText(context, "下架", Toast.LENGTH_SHORT).show();
                pro_shelves = "1";
                break;
            case R.id.iv_btn_product_tijiao:
                addcommoditydia();
                String pro_name = mEtProductName.getText().toString().trim();
                String pro_price = mEtProductPrice.getText().toString().trim();
                String pro_h_price = mEtProductActivePrice.getText().toString().trim();
                String pro_inventory = mEtProductInventory.getText().toString().trim();
                String pro_taste = mEtProductTaste.getText().toString().trim();
                String pro_kg = mEtProductWeight.getText().toString().trim();
                String pro_origin = mEtProductAddress.getText().toString().trim();
                String pro_size = mEtProductSize.getText().toString().trim();
                String pro_color = mEtProductColor.getText().toString().trim();
                String pro_ml = mEtProductVolume.getText().toString().trim();
                String pro_content = mEtProductDec.getText().toString().trim();
                if (TextUtils.isEmpty(pro_name) || TextUtils.isEmpty(pro_price)) {
                    Toast.makeText(context, "商品名称,价格不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ImageList.size()<1){
                    Toast.makeText(context, "至少选择一张图片", Toast.LENGTH_SHORT).show();
                    return;
                }


                HashMap<String, String> map = new HashMap<String, String>();
                if (TextUtils.isEmpty(pro_brand)) {
                    pro_brand = "";
                }if (TextUtils.isEmpty(pro_shelves)) {
                    type_id = "";
                }if (TextUtils.isEmpty(type_id)) {
                    type_id = "";
                }
                map.put("pro_name", pro_name);
                map.put("pro_shelves", pro_shelves);
                map.put("brand_id", pro_brand);
                map.put("type_id", type_id);
                Log.d("mafuhua", "type_id" + type_id);
                map.put("pro_price", pro_price);
                map.put("pro_h_price", pro_h_price);
                map.put("pro_inventory", pro_inventory);
                map.put("pro_taste", pro_taste);
                map.put("shop_userid", MainActivity.userid);
                map.put("pro_kg", pro_kg);
                map.put("pro_origin", pro_origin);
                map.put("pro_size", pro_size);
                map.put("pro_color", pro_color);
                map.put("pro_ml", pro_ml);
                map.put("pro_content", pro_content);
                XUtils.xUtilsPost(ContactURL.SHOP_ADD_PRO, map, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Log.d("mafuhua", "result" + result.toString());
                        resultid = result.toString();
                        if (resultid != null) {
                            sendComPic();

                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.d("mafuhua", "result" + isOnCallback);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                //sendComPic();
                // finish();
                break;
            case R.id.iv_btn_add_selector_image:
                Intent intent = new Intent(context, SelectorImageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityForResult(intent, 100);
                break;
        }
    }
    private void addcommoditydia() {
        mypDialog = new ProgressDialog(context);
        //实例化
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置进度条风格，风格为圆形，旋转的
        //设置ProgressDialog 标题
        mypDialog.setMessage("正在提交");
        //设置ProgressDialog 提示信息
        mypDialog.setIndeterminate(false);
        //设置ProgressDialog 的进度条是否不明确
        mypDialog.setCancelable(true);
        //设置ProgressDialog 是否可以按退回按键取消
        mypDialog.show();
        //让ProgressDialog显示

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
                convertView = View.inflate(context, R.layout.layout_selector_gridview, null);
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
                iditemimage = (ImageView) root.findViewById(R.id.iv_grid_item_image);
            }
        }
    }

}
