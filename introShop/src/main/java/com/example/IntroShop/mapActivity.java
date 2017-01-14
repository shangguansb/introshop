package com.example.IntroShop;

import android.app.Activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class mapActivity extends AppCompatActivity {
    private MapView mapView;
    private AMap aMap;
    private LocationManager locationManager;
    public AMapLocationClient maMapLocationClient = null;

    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);
        locationManager = (LocationManager) getSystemService(Context
                .LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.mapview);
        // 必须回调MapView的onCreate()方法
        mapView.onCreate(savedInstanceState);
        init();
        RadioButton rb = (RadioButton) findViewById(R.id.gps);
        maMapLocationClient = new AMapLocationClient(getApplicationContext());

//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        maMapLocationClient.setLocationOption(mLocationOption);
//启动定位
//        maMapLocationClient.startLocation();
        maMapLocationClient.setLocationListener(new AMapLocationListener() {

            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        updatePosition(amapLocation);
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        amapLocation.getLongitude();//获取经度
                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        amapLocation.getCity();//城市信息
                        amapLocation.getDistrict();//城区信息
                        amapLocation.getStreet();//街道信息
                        amapLocation.getStreetNum();//街道门牌号信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        amapLocation.getAoiName();//获取当前定位点的AOI信息
                        Log.e("sdA", "================" + amapLocation.getLatitude());
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }

        });

//
        // 为GPS单选按钮设置监听器
        RadioButton shou = (RadioButton) findViewById(R.id.shou);

        rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView
                    , boolean isChecked) {

                // 如果该单选框已经被勾选
                if (isChecked) {
                    maMapLocationClient.startLocation();

                } else {
                    maMapLocationClient.stopLocation();
                }
            }
        });
        Button bn = (Button) findViewById(R.id.show);
        final TextView latTv = (TextView) findViewById(R.id.lat);
        final TextView lngTv = (TextView) findViewById(R.id.lng);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的经度、纬度值
                String lng = lngTv.getEditableText().toString().trim();
                String lat = latTv.getEditableText().toString().trim();
                double dLat = Double.parseDouble(lat);
                double dLng = Double.parseDouble(lng);
                if (lng.equals("") || lat.equals("")) {
                    Toast.makeText(mapActivity.this, "请输入有效的经度、纬度!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // 设置根据用户输入的地址定位
//                    ((RadioButton) findViewById(R.id.shou)).setChecked(true);
                    // 将用户输入的经、纬度封装成LatLng
                    LatLng latLng = new LatLng(dLat, dLng);  // ①
                    // 创建一个设置经纬度的CameraUpdate
                    CameraUpdate cu2 = CameraUpdateFactory.zoomTo(15);
                    // 设置地图的默认放大级别
                    aMap.moveCamera(cu2);// ③
                    CameraUpdate cu = CameraUpdateFactory.changeLatLng(latLng);  // ②
                    // 更新地图的显示区域、

                    aMap.moveCamera(cu);

                    // 创建MarkerOptions对象
                    MarkerOptions markerOptions = new MarkerOptions();
                    // 设置MarkerOptions的添加位置
                    markerOptions.position(latLng);
                    // 设置MarkerOptions的标题
                    markerOptions.title("上海国际汇总");
                    // 设置MarkerOptions的摘录信息
                    markerOptions.snippet("上海");
                    // 设置MarkerOptions的图标
                    markerOptions.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    markerOptions.draggable(true);
                    // 添加MarkerOptions（实际上就是添加Marker）
                    Marker marker = aMap.addMarker(markerOptions);
                    marker.showInfoWindow(); // 设置默认显示信息窗
                    // 创建MarkerOptions、并设置它的各种属性
                    MarkerOptions markerOptions1 = new MarkerOptions();
                    markerOptions1.position(new LatLng(dLat + 0.001, dLng))
                            // 设置标题
                            .title("上海步行街")
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                            .draggable(true);
                    // 使用集合封装多个图标，这样可为MarkerOptions设置多个图标
                    ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
                    giflist.add(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    giflist.add(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    giflist.add(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    // 在创建一个MarkerOptions、并设置它的各种属性
                    MarkerOptions markerOptions2 = new MarkerOptions()
                            .position(new LatLng(dLat - 0.001, dLng))
                                    // 为MarkerOptions设置多个图标
                            .icons(giflist)
                            .title("上海滩")
                            .draggable(true)
                                    // 设置图标的切换频率
                            .period(10);
                    // 使用ArrayList封装多个MarkerOptions，即可一次添加多个Marker
                    ArrayList<MarkerOptions> optionList = new ArrayList<MarkerOptions>();
                    optionList.add(markerOptions1);
                    optionList.add(markerOptions2);
                    // 批量添加多个Marker
                    aMap.addMarkers(optionList, true);

                }
            }
        });
    }

    private void updatePosition(Location location) {
        Log.e("dawd", "--------update");
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        // 创建一个设置经纬度的CameraUpdate
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);
        // 更新地图的显示区域
        aMap.moveCamera(cu);
        // 清除所有Marker等覆盖物
        aMap.clear();
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pos);
        // 设置MarkerOptions使用自定义图标
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));

        markerOptions.draggable(true);
        // 添加MarkerOptions（实际上是添加Marker）
        Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();

    }

    // 初始化AMap对象
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            // 创建一个设置放大级别的CameraUpdate
            CameraUpdate cu = CameraUpdateFactory.zoomTo(15);
            // 设置地图的默认放大级别
            aMap.moveCamera(cu);
            // 创建一个更改地图倾斜度的CameraUpdate
            CameraUpdate tiltUpdate = CameraUpdateFactory.changeTilt(30);
            // 改变地图的倾斜度
            aMap.moveCamera(tiltUpdate);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 必须回调MapView的onResume()方法
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 必须回调MapView的onPause()方法
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 必须回调MapView的onSaveInstanceState()方法
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须回调MapView的onDestroy()方法
        mapView.onDestroy();
    }
}

