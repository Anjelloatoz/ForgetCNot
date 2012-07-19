package com.svgonbatik.forgetcnot;

import java.util.*;

import com.google.android.maps.MapActivity;
import android.os.Bundle;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapController;
import com.google.android.maps.GeoPoint;
import android.location.GpsStatus;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import android.util.DisplayMetrics;
import java.io.ObjectInputStream;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.view.KeyEvent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.Gravity;
import android.media.SoundPool;
import android.media.AudioManager;
import android.media.SoundPool.OnLoadCompleteListener;
import android.location.Criteria;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.graphics.Matrix;

/*Map Keys
 * N10: 0BvGia5neQIPdiPewLDdpKjsjO67XJQxTUE4Sqw
 * Release: 0BvGia5neQIPyevEaO47uDEa21odiJbj6_6k4kg
 * Lasheen: 0BvGia5neQIMtiyvpmNSv5a-3KrqVV6L2MCV5gA
 */

public class GoogleMapsActivity extends MapActivity implements LocationListener{
	int version_id = 3;
	MapView mapView;
	MapController mc;
	GeoPoint p;
	Path c_zone_path;
	int location_segment_number = 250;
	GeoPoint me = new GeoPoint(0,0);
	GeoPoint test_site = new GeoPoint(51516251, 0);
	String drive_direction_url = "http://maps.google.com/maps?f=d&hl=en";
	Paint c_boundary_paint = new Paint();
	Paint drive_path_paint = new Paint();
	Paint distance_paint = new Paint();
	int xs[] = {51518123, 51517768, 51517025, 51516251, 51515821, 51515348, 51514966, 51514403, 51514042, 51513900, 51513447, 51513571, 51513292, 51512585, 51511591, 51511116, 51510567, 51510226, 51509095, 51508302, 51507633, 51506968, 51506476, 51505806, 51505412, 51505067, 51504718, 51504155, 51503623, 51503523, 51503148, 51502720, 51502285, 51502025, 51501878, 51501626, 51501171, 51500730, 51500255, 51499300, 51498707, 51498375, 51498380, 51498411, 51498325, 51498255, 51498016, 51497961, 51497840, 51497431, 51496881, 51496441, 51496344, 51495523, 51494657, 51494159, 51493843, 51493800, 51493173, 51492633, 51491849, 51491173, 51490747, 51490253, 51489650, 51488709, 51488305, 51487268, 51486914, 51486632, 51486398, 51486398, 51486556, 51486747, 51487105, 51487076, 51487155, 51487335, 51487600, 51488641, 51488953, 51489195, 51489593, 51489956, 51490415, 51490648, 51490916, 51491019, 51491143, 51491341, 51491617, 51492596, 51492980, 51493532, 51494693, 51495027, 51495468, 51495747, 51495790, 51495799, 51495705, 51495390, 51495274, 51495179, 51494892, 51494635, 51494393, 51494239, 51494269, 51494269, 51494320, 51494403, 51494426, 51494468, 51494965, 51495099, 51495012, 51494919, 51495398, 51496129, 51496637, 51496991, 51497419, 51497818, 51499985, 51500733, 51502149, 51503010, 51503282, 51503066, 51504543, 51506349, 51507251, 51509283, 51510071, 51511097, 51511392, 51511363, 51513062, 51513667, 51514346, 51514703, 51515020, 51515406, 51518151, 51518748, 51520318, 51521109, 51522643, 51522893, 51523672, 51523567, 51523719, 51524284, 51524642, 51525999, 51526105, 51526113, 51525693, 51525278, 51525184, 51525299, 51525653, 51526045, 51526979, 51527237, 51527436, 51527862, 51528538, 51530707, 51529533, 51531335, 51531715, 51531807, 51531672, 51531648, 51531483, 51531306, 51529766, 51529320, 51528532, 51530218, 51530409, 51530494, 51530206, 51529845, 51529054, 51527978, 51526505, 51525500, 51524627, 51523524, 51524741, 51524083, 51523790, 51523735, 51523412, 51523440, 51523715, 51523867, 51523856, 51523459, 51523057, 51522818, 51522736, 51522823, 51523096, 51523308, 51523019, 51522874, 51522573, 51522215, 51522002, 51520781, 51520398, 51521528, 51520590, 51520113, 51519246, 51518727, 51518123};
	int ys[] = {-166774, -166565, -165411, -164303, -163735, -163033, -162463, -161570, -161103, -160770, -159993, -158791, -158033, -157679, -157215, -156950, -156626, -156267, -154591, -153770, -153175, -152287, -151832, -150940, -150757, -150579, -150685, -150762, -150630, -149950, -149441, -149615, -149825, -150450, -151026, -150866, -150100, -149389, -148717, -147698, -146858, -146684, -146308, -145060, -144391, -143823, -142929, -142482, -141694, -141616, -141336, -141585, -142296, -141843, -141076, -140522, -140100, -139830, -138553, -137438, -135854, -134354, -133519, -132447, -131018, -128978, -128170, -125727, -124988, -123236, -121199, -120575, -119319, -118176, -116064, -115078, -113541, -112895, -112371, -111489, -111106, -110869, -109581, -108233, -107292, -106754, -106232, -105815, -105088, -104086, -103296, -101843, -101270, -100970, -101193, -101576, -101367, -101111, -100737, -100267, -99809, -99759, -99470, -98974, -97502, -96056, -95117, -94629, -93587, -92569, -92195, -90257, -89360, -86763, -86428, -86001, -85370, -84923, -84130, -83086, -82401, -81650, -80716, -80071, -78979, -78617, -77993, -79200, -79030, -77468, -76370, -75321, -74678, -74243, -75185, -75367, -74942, -73278, -73525, -73635, -74123, -73956, -73025, -72310, -74525, -74825, -74708, -75433, -77580, -78296, -79444, -80441, -80592, -80594, -81113, -83745, -84284, -84929, -86738, -87070, -87473, -88060, -88293, -88023, -88412, -88677, -89078, -90618, -93166, -101724, -102498, -105098, -105866, -106464, -109104, -110224, -111660, -113844, -115599, -115555, -119601, -121105, -121511, -122406, -123084, -123896, -125878, -128621, -132693, -135607, -134734, -137379, -138220, -141288, -142765, -143239, -143961, -144351, -144519, -144522, -144883, -144896, -145271, -145804, -146550, -147316, -147802, -148049, -150454, -152538, -154247, -156066, -157295, -156703, -159287, -159736, -165163, -165491, -165774, -166375, -166775};
	GeoPoint drive_route[];
	List<Integer> xlist = new ArrayList<Integer>();
	List<Integer> ylist = new ArrayList<Integer>();
	
	Calendar c;
	int last_time;
	int last_distance;
	TextView distance_label;
	int dist;
	int velocity = 0;
	boolean inbounds = false;
	boolean cc = false;
	boolean nz = false;
	boolean tap = false;
	float volume;
	Point pt1 = null;
	Point pt2 = null;
	private String provider;
	Bitmap c_location_bmp;
	Bitmap user_location_bmp;
	Bitmap park_site_bmp;
	String label_text = "";

	int[][] coordinate_segment_array = null;
	
	private SoundPool soundPool;
	private int soundID;
	boolean loaded = false;
	boolean sound = true;
	LocationManager mlocManager;
	boolean contains;
	boolean ready = false;
	boolean msg_shown = false;
	boolean autozoom = true;
	boolean autopan = true;
	private NotificationManager mManager;
	private static final int APP_ID = 0;
	boolean entered = false;
	ArrayList<ParkSite> park_list = new ArrayList<ParkSite>();
	float px_res = 0;
	AnimatedPanel slider;
	LinearLayout notice_layout;
	String greetingheading = "Sorry!";
	String greetingtext = "Could not connect to the maps. Make sure you have Internet connection and try again.";
	String greetingbuttontext = "Ok";
	int live = 0;
	Bitmap user_rotate;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getLc();
        c = Calendar.getInstance();
        slider = (AnimatedPanel)findViewById(R.id.animated_panel);
        notice_layout = (LinearLayout)findViewById(R.id.notice_layout);
        last_time = (int)(System.currentTimeMillis()/1000);
        distance_label = new TextView(this);
        distance_label.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        distance_label.setTextSize(20);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			
		public void onLoadComplete(SoundPool soundPool, int sampleId, int status){
			loaded = true;
			}
		});
		soundID = soundPool.load(this, R.raw.r3, 1);
		
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        System.out.println("DPI is: "+dm.densityDpi);
        px_res = dm.densityDpi/160;
        user_location_bmp = BitmapFactory.decodeResource(getResources(), R.drawable.greenmarker);
        user_rotate = user_location_bmp;
        park_site_bmp = BitmapFactory.decodeResource(getResources(), R.drawable.park_site_icon);
        
        getParks("http://www.alliancerational.com/Script/parkserver.php");

        live = setGreeting(getGreeting(version_id));
        setMessageSlider(greetingheading, greetingtext, greetingbuttontext, "");
        ready = true;
    	ccInit();

    	c_boundary_paint.setColor(Color.RED);
        drive_path_paint.setColor(Color.MAGENTA);
        c_boundary_paint.setStyle(Paint.Style.STROKE);
		drive_path_paint.setStyle(Paint.Style.STROKE);
        c_zone_path = new Path();
        if(dm.densityDpi == 240){
        	c_boundary_paint.setStrokeWidth(3);
        	drive_path_paint.setStrokeWidth(5);
        } else if(dm.densityDpi == 120){
        	c_boundary_paint.setStrokeWidth(1);
        	drive_path_paint.setStrokeWidth(2);
        }else{
        	c_boundary_paint.setStrokeWidth(3);
        	drive_path_paint.setStrokeWidth(5);
        }
        
        mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = mlocManager.getBestProvider(criteria, false);

        LocationListener mlocListener = new GeoUpdateHandler();
        
        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
        mlocManager.addGpsStatusListener(gpsListener);
        if (!mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){  
            createGpsDisabledAlert();  
      }

        mapView = (MapView) findViewById(R.id.mapView);

        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);
        View zoomView = mapView.getZoomControls();
        zoomLayout.addView(zoomView, 
        	    new LinearLayout.LayoutParams(
        	        LayoutParams.WRAP_CONTENT, 
        	        LayoutParams.WRAP_CONTENT));

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.distance_layout);
        rl.addView(distance_label);
       	mapView.displayZoomControls(true);

        mc = mapView.getController();
        String coordinates[] = {"0", "51.5"};
        double lat = Double.parseDouble(coordinates[0]);
        double lng = Double.parseDouble(coordinates[1]);
        p = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
        
        MapOverlay mapOverlay = new MapOverlay();
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);        
 
        mapView.setSatellite(false);
        mapView.invalidate();
        soundModule();
        mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        showNotification("");
    }
	
/*	public boolean onCreateOptionsMenu(Menu menu){
		settings();
		return true;
		}*/
	
/*	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {   
        if(item.getTitle()=="Exit"){System.exit(0);}
        return true;   
    } 
	
	@Override
	protected void onResume() {
		super.onResume();
		mlocManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		mlocManager.removeUpdates(this);
	}

	public void onLocationChanged(Location location) {
		System.out.println("onLocationChaned called.........");
		int lat = (int) (location.getLatitude());
		int lng = (int) (location.getLongitude());
		Toast.makeText(getApplicationContext(), "Location: "+lat+", "+lng, Toast.LENGTH_SHORT).show();
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {}

	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	public void onProviderDisabled(String provider) {}
	
	public void initControls(){
        TransparentPanel tp = (TransparentPanel)findViewById(R.id.transparent_panel);
        Button map_button = (Button)findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	mapView.setSatellite(!mapView.isSatellite());
            	mapView.invalidate();
            }
        });
        
        final Button zoom_button = (Button)findViewById(R.id.zoom_button);
        zoom_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	autozoom = !autozoom;
            	if(autozoom){
            		zoom_button.setTextColor(Color.rgb(224,28,11));
            	}
            	else{
            		zoom_button.setTextColor(Color.GRAY);
            	}
            }
        });
        
        final Button c_button = (Button)findViewById(R.id.c_button);
        c_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	payPageLoader();
            }
        });
        
        final Button pan_button = (Button)findViewById(R.id.pan_button);
        pan_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	autopan = !autopan;
            	if(autopan){
            		pan_button.setTextColor(Color.rgb(224,28,11));
            	}
            	else{
            		pan_button.setTextColor(Color.GRAY);
            	}
            }
        });
        
        final Button beep_button = (Button)findViewById(R.id.beep_button);
        beep_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	sound = !sound;
            	if(sound){
            		beep_button.setTextColor(Color.rgb(224,28,11));
            	}
            	else{
            		beep_button.setTextColor(Color.GRAY);
            	}
            }
        });
        tap = false;
	}
	
	public void onBackPressed() {
		System.exit(0);
    }
	
	public void setParkSelectListener(View view, final ParkSite park_site){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	slider.setLayoutAnimExit(slider, slider.getContext());
            	getUrl(me, new GeoPoint(park_site.getLatitude(), park_site.getLongitude()));
            	mc.setZoom(17);
            	mapView.setSatellite(false);
            	mapView.invalidate();
            	autozoom = false;
            	postUserStat(park_site.getName(), me.getLatitudeE6()/ 1.0E6, me.getLongitudeE6()/ 1.0E6);
            }
        });
    }
	
	public void setSliderExitListener(View view){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("Slider Exit called.");
            	slider.setLayoutAnimExit(slider, slider.getContext());
            }
        });
    }
	
	public void setExitListener(View view){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("System Exit called.");
            	System.exit(0);
            }
        });
    }
	
	public void setGPSListener(View view){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent gpsOptionsIntent = new Intent(  
                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
                startActivity(gpsOptionsIntent);
                slider.setLayoutAnimExit(slider, slider.getContext());
            }
        });
    }
	
	public void setParkCancelListener(View view){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	slider.setLayoutAnimExit(slider, slider.getContext());
            }
        });
    }
	
	public void initCallListener(View view){
    	view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	slider.setLayoutAnimExit(slider, slider.getContext());
            	call(((TextView)v).getText().toString());
            }
        });
    }
	
	public void ccInit(){
		cc = true;
		
		c_location_bmp = BitmapFactory.decodeResource(getResources(), R.drawable.redmarker);
		label_text = "Congestion Charge Zone";
		for(int i = 0; i< xs.length; i++){
        	xlist.add(xs[i]);
        	ylist.add(ys[i]);
        }
		initControls();
	}
	
/*	public void settings(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final CharSequence[] function_options = {"Auto Zoom", "Auto Pan"};
        builder.setTitle("Settings");
        final boolean values[] = {autozoom, autopan};
        builder.setMultiChoiceItems(function_options, values, new DialogInterface.OnMultiChoiceClickListener(){
            public void onClick(DialogInterface dialog, int item, boolean b){
            	if(item == 0){
            		autozoom = b;
            	}
            	else if(item == 1){
            		autopan = b;
            	}
            }
        });
        
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
        	public void onClick(DialogInterface dialog, int item){}
        });
                
        AlertDialog opening_dialog = builder.create();
        opening_dialog.show();
	}*/

	public void drawCMap(){
		Point Tp = new Point();
		c_zone_path.reset();
		mapView.getProjection().toPixels(new GeoPoint(xs[0], ys[0]), Tp);
        c_zone_path.moveTo(Tp.x, Tp.y);
        for(int x = 1; x<xs.length; x++){
			mapView.getProjection().toPixels(new GeoPoint(xs[x], ys[x]), Tp);
        	c_zone_path.lineTo(Tp.x, Tp.y);
        }
	}
	
	public void rotatePointer(float angle){
		System.out.println("Rotation called: "+angle);
		Matrix matrix = new Matrix();
		matrix.setRotate(angle, 0, 0);
		user_rotate = Bitmap.createBitmap(user_location_bmp, 0, 0, user_location_bmp.getWidth(), user_location_bmp.getHeight(), matrix, true);
	}

	class MapOverlay extends Overlay{
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when)
		{
			super.draw(canvas, mapView, shadow);
			drawCMap();
            if(xlist.isEmpty()){
				return true;
			}
            
            for(int x = 0; x < park_list.size(); x++){
            	Point point = new Point();
    			mapView.getProjection().toPixels(new GeoPoint((int)(park_list.get(x).getLatitude()), (int)(park_list.get(x).getLongitude())), point);
            	canvas.drawBitmap(park_site_bmp, point.x-(park_site_bmp.getWidth()/2), point.y-(park_site_bmp.getHeight()), null);
            }
            
            if(drive_route!=null){
            	Path drive_path = new Path();
                Point Dp = new Point();
                mapView.getProjection().toPixels(drive_route[2], Dp);
                drive_path.moveTo(Dp.x, Dp.y);
            	for(int i = 1; i < drive_route.length; i++){
                	mapView.getProjection().toPixels(drive_route[i], Dp);
                	drive_path.lineTo(Dp.x, Dp.y);
                	canvas.drawPath(drive_path, drive_path_paint);
                }
            }
            
            if(pt1 !=null && pt2 != null){
            	Point Tpoint = new Point();
    			mapView.getProjection().toPixels(new GeoPoint(pt1.x, pt1.y), Tpoint);
            	canvas.drawBitmap(c_location_bmp, Tpoint.x-(c_location_bmp.getWidth()/2), Tpoint.y-(c_location_bmp.getHeight()), null);
            	Point Tpoint2 = new Point();
    			mapView.getProjection().toPixels(new GeoPoint(pt2.x, pt2.y), Tpoint2);
            	canvas.drawBitmap(c_location_bmp, Tpoint2.x-(c_location_bmp.getWidth()/2), Tpoint2.y-(c_location_bmp.getHeight()), null);
            }
            canvas.drawPath(c_zone_path, c_boundary_paint);
			Point mep = new Point();
			mapView.getProjection().toPixels(me, mep);
            canvas.drawBitmap(user_rotate, mep.x-(user_rotate.getWidth()/2), mep.y-(user_rotate.getHeight()/2), null);

            return true;
        }

		public boolean onTap(GeoPoint p, MapView mapView) {
			Point screenPts = new Point();
			Point parkPts = new Point();
			mapView.getProjection().toPixels(p, screenPts);
			screenPts.y = screenPts.y+20;
			screenPts.x = screenPts.x+6;
			GeoPoint park_point;
	   FOR: for(int x = 0; x < park_list.size(); x++){
				park_point = new GeoPoint(park_list.get(x).getLatitude(), park_list.get(x).getLongitude());
				mapView.getProjection().toPixels(park_point, parkPts);
				if(distanceTo(screenPts, parkPts) < 10){
					setParkNoticeBoard(park_list.get(x));
					slider.setLayoutAnimEntrance(slider, slider.getContext());
					break FOR;
				}
			}
			if(!tap){
//				settings();
				return true;
			}
//			Point screenPts = new Point();
//			mapView.getProjection().toPixels(p, screenPts);
			xlist.add(p.getLatitudeE6());
			ylist.add(p.getLongitudeE6());
			System.out.println("The length of xlist is: "+xlist.size()+" "+xlist.get(0));
	    	System.out.println("P.lineTo("+p.getLatitudeE6()+", "+p.getLongitudeE6()+");");
        	return true;
    	}
	}
	 
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {         
        switch (keyCode) 
        {
            case KeyEvent.KEYCODE_3:
                mc.zoomIn();
                break;
            case KeyEvent.KEYCODE_1:
                mc.zoomOut();
                break;
            case KeyEvent.KEYCODE_5:
                this.printCoordinates();
                break;
        }
        System.out.println("Manual Zoom entry: "+mapView.getZoomLevel());
        return super.onKeyDown(keyCode, event);
    }
    
    public void printCoordinates(){
    	System.out.print("The X coords: {");
    	for(int i = 0; i < xlist.size(); i++){
    		System.out.print(xlist.get(i));
    		System.out.print(", ");
    	}
    	System.out.println("}");
    	
    	System.out.print("The Y coords: {");
    	for(int i = 0; i < ylist.size(); i++){
    		System.out.print(ylist.get(i));
    		System.out.print(", ");
    	}
    	System.out.println("}");
    }
    
    public boolean contains(double x, double y){
    	if (xs.length <= 2) {
    	    return false;
    	}
    	int hits = 0;

    	int lastx = xs[xs.length-1];
    	int lasty = ys[ys.length-1];
    	int curx, cury;

    	// Walk the edges of the polygon
    	for (int i = 0; i < xs.length; lastx = curx, lasty = cury, i++) {
    	    curx = xs[i];
    	    cury = ys[i];

    	    if (cury == lasty) {
    		continue;
    	    }

    	    int leftx;
    	    if (curx < lastx) {
    		if (x >= lastx) {
    		    continue;
    		}
    		leftx = curx;
    	    } else {
    		if (x >= curx) {
    		    continue;
    		}
    		leftx = lastx;
    	    }

    	    double test1, test2;
    	    if (cury < lasty) {
    		if (y < cury || y >= lasty) {
    		    continue;
    		}
    		if (x < leftx) {
    		    hits++;
    		    continue;
    		}
    		test1 = x - curx;
    		test2 = y - cury;
    	    } else {
    		if (y < lasty || y >= cury) {
    		    continue;
    		}
    		if (x < leftx) {
    		    hits++;
    		    continue;
    		}
    		test1 = x - lastx;
    		test2 = y - lasty;
    	    }

    	    if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
    		hits++;
    	    }
    	}
    	return ((hits & 1) != 0);
    }

    private boolean containsPreserved(double x, double y){
    	if (xlist.size() <= 2) {
    	    return false;
    	}
    	int hits = 0;

    	int lastx = xlist.get(xlist.size() - 1);
    	int lasty = ylist.get(ylist.size() - 1);
    	int curx, cury;

    	// Walk the edges of the polygon
    	for (int i = 0; i < xlist.size(); lastx = curx, lasty = cury, i++) {
    	    curx = xlist.get(i);
    	    cury = ylist.get(i);

    	    if (cury == lasty) {
    		continue;
    	    }

    	    int leftx;
    	    if (curx < lastx) {
    		if (x >= lastx) {
    		    continue;
    		}
    		leftx = curx;
    	    } else {
    		if (x >= curx) {
    		    continue;
    		}
    		leftx = lastx;
    	    }

    	    double test1, test2;
    	    if (cury < lasty) {
    		if (y < cury || y >= lasty) {
    		    continue;
    		}
    		if (x < leftx) {
    		    hits++;
    		    continue;
    		}
    		test1 = x - curx;
    		test2 = y - cury;
    	    } else {
    		if (y < lasty || y >= cury) {
    		    continue;
    		}
    		if (x < leftx) {
    		    hits++;
    		    continue;
    		}
    		test1 = x - lastx;
    		test2 = y - lasty;
    	    }

    	    if (test1 < (test2 / (lasty - cury) * (lastx - curx))) {
    		hits++;
    	    }
    	}
    	return ((hits & 1) != 0);
    }
    
    public void populator(double x, double y){
    	xlist.add((int)x);
    	ylist.add((int)y);
    }
    
    public void calcDist(){
    	int test_array[];
    	if(location_segment_number == 250){
    		test_array = xs;
    	}
    	else{
    		test_array = coordinate_segment_array[location_segment_number];
    	}
    	int tmp_number = 0;
    	
    	double distance = ptSegDist(new Point(xlist.get(0), ylist.get(0)), new Point(xlist.get(1), ylist.get(1)), me.getLatitudeE6(), me.getLongitudeE6());
    	pt1 = new Point(xlist.get(0), ylist.get(0));
    	pt2 = new Point(xlist.get(1), ylist.get(1));
    	for(int i = 0; i < test_array.length-1; i++){
    		System.out.println("In the calc dist.");
    		
    		if(i < xlist.size()-1){
        		double tmp_dist = ptSegDist(new Point(xlist.get(i), ylist.get(i)), new Point(xlist.get(i+1), ylist.get(i+1)), me.getLatitudeE6(), me.getLongitudeE6());
        		if(distance>tmp_dist){
        			distance = tmp_dist;
        			pt1 = new Point(xlist.get(i), ylist.get(i));
        			pt2 = new Point(xlist.get(i+1), ylist.get(i+1));
        			tmp_number = i;
        		}
    		}

    	}
    	
    	if(location_segment_number == 250){
			System.out.println("In the for loop: "+location_segment_number);
			location_segment_number = tmp_number;
		}
		else{
			location_segment_number = test_array[tmp_number];
		}

    	/*------------------------------------------*/
    	System.out.println("Number is: "+location_segment_number);
    	xlist.clear();
    	ylist.clear();
    	for(int i = 0; i < coordinate_segment_array[location_segment_number].length; i++){
    		xlist.add(xs[coordinate_segment_array[location_segment_number][i]]);
    		ylist.add(ys[coordinate_segment_array[location_segment_number][i]]);
    	}
    	/*------------------------------------------*/

    	Point closest_point = getClosestPoint(pt1, pt2, new Point(me.getLatitudeE6(), me.getLongitudeE6()));
    	
    	Location l1 = new Location("start");
    	l1.setLatitude(mD2D(closest_point.x));
    	l1.setLongitude(mD2D(closest_point.y));

    	Location l2 = new Location("end");
    	l2.setLatitude(mD2D(me.getLatitudeE6()));
    	l2.setLongitude(mD2D(me.getLongitudeE6()));
    	
    	dist = (int)l1.distanceTo(l2);
    	calcVelocity(dist);
    	int dist_color;
    	if(dist > 1000){
    		dist_color = Color.GREEN;
    		distance_label.setTextSize(25);
    	}
    	else{
    		dist_color = Color.RED;
    		distance_label.setTextSize(40);
    	}
    	distance_label.setTextColor(dist_color);
    	
    	distance_label.setGravity(Gravity.RIGHT);

		System.out.println("Dist is: "+dist);

    	if(autozoom){
    	if(dist<50){
    		System.out.println("Zoom: 19");
    		mc.setZoom(19);
    	}
    	else if(dist<55){
    		mc.setZoom(18);
    		System.out.println("Zoom: 18");
    	}
    	else if(dist<105){
    		mc.setZoom(17);
    		System.out.println("Zoom: 17");
    	}
    	else if(dist<200){
    		mc.setZoom(16);
    		System.out.println("Zoom: 16");
    	}
    	else if(dist<410){
    		mc.setZoom(15);
    		System.out.println("Zoom: 15");
    	}
    	else if(dist<995){
    		mc.setZoom(14);
    		System.out.println("Zoom: 14");
    	}
    	else if(dist<1870){
    		mc.setZoom(13);
    		System.out.println("Zoom: 13");
    	}
/*    	else if(dist<3400){
    		mc.setZoom(12);
    		System.out.println("Zoom: 12");
    	}
    	else if(dist<5300){
    		mc.setZoom(11);
    		System.out.println("Zoom: 11");
    	}*/
    	else if(dist<7000){
    		mc.setZoom(12);
    		System.out.println("Zoom: 12");
    	}
    	else if(dist<10700){
    		mc.setZoom(11);
    		System.out.println("Zoom: 11");
    	}
    	else if(dist<20360){
    		mc.setZoom(10);
    		System.out.println("Zoom: 10");
    	}
    	else if(dist<43225){
    		mc.setZoom(9);
    		System.out.println("Zoom: 7");
    	}
    	else{
    		mc.setZoom(8);
    		System.out.println("Zoom: 6");
    	}
    	}
    }
    
    public void soundModule(){
    	System.out.println("Sound module called.");
    	AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    	float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    	float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    	volume = actualVolume / maxVolume;
    		Thread t = new Thread(){
    			public void run(){
    				while(true){
    					if(dist < 3000 && dist > 0 && !inbounds && sound && velocity > 0){
    						if(loaded){    		    				
        						soundPool.play(soundID, volume, volume, 1, 0, 1f);
        						
        					}
        	    			
        	    			try{
        	    				int sleeptime = (int)dist*10;
        	    				if(sleeptime>1000){
        	    					Thread.sleep((int)dist*10);
        	    				}
        	    				else{
        	    					Thread.sleep(1000);
        	    				}
        	    				
        	    			}
        	    			catch(Exception e){
        	    				System.out.println("Exception at sounds: "+e);
        	    			} 
    					}    					   	    			
    	    		}
    			}
    		};
    		t.setPriority(1);
    		if(!t.isAlive()){
    			t.start();
    		}    		
    }
    
    public void calcVelocity(int new_dist){
//    	int current_time = (int)(System.currentTimeMillis()/1000);
//    	System.out.println("last_time: "+last_time);
//    	System.out.println("current_time: "+current_time);
//    	System.out.println("Last Distance: "+last_distance);
//    	System.out.println("New Distance: "+new_dist);
//    	int time_gap = current_time - last_time;
    	velocity = (last_distance - new_dist);
//    	Toast.makeText(getApplicationContext(), "Velocity: "+(last_distance-new_dist), Toast.LENGTH_SHORT).show();
    	last_distance = new_dist;
//    	last_time = current_time;
    }
    
    public void paymentMessage(){
    	if(msg_shown){
    		return;
    	}
    	else
    		msg_shown = true;
    	AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
    	alertbox.setTitle("Congestion Charge");
    	alertbox.setMessage("You are now entering the London congestion charge zone. Do you want to pay the congestion charge now?");
    	alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface arg0, int arg1){
    			System.out.println("User pressed yes.");
    			payPageLoader();
    		}
    	});
    	alertbox.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface arg0, int arg1){
    			System.out.println("User pressed not now.");
    		}
    	});
    	alertbox.show();
    	showNotification("You are entering the London congestion charge zone.");
    }
    
    private void payPageLoader(){
    	Uri uri = Uri.parse( "http://www.tfl.gov.uk/roadusers/congestioncharging/6743.aspx" );
		startActivity( new Intent( Intent.ACTION_VIEW, uri ) );
    }
    
    private static Point getClosestPoint(Point pt1, Point pt2, Point p){
    	double u = ((p.x-pt1.x)*(pt2.x-pt1.x)+(p.y-pt1.y)*(pt2.y-pt1.y))/(sqr(pt2.x-pt1.x)+sqr(pt2.y-pt1.y));
    	if (u > 1.0)
    		return new Point(pt2.x, pt2.y);
    	else if (u <= 0.0)
    		return new Point(pt1.x, pt1.y);
    	else
    		return new Point((int)(pt2.x*u+pt1.x*(1.0-u)+0.5), (int)(pt2.y*u+pt1.y*(1.0-u)+0.5));
    	}
    private static double sqr(double x){
    	return x*x;
    }
    
    public  double mD2D(int microDegrees) {
        return microDegrees / 1E6;
    }

    public double ptSegDist(Point p, Point q, double PX, double PY) {
    	return ptSegDist(p.x, p.y, q.x, q.y, PX, PY);
    }
    
    public static double ptSegDist(double X1, double Y1,
    		double X2, double Y2,
    		double PX, double PY) {
    	
    		return Math.sqrt(ptSegDistSq(X1, Y1, X2, Y2, PX, PY));
    	}
    
    public int distanceTo(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return (int)Math.sqrt(dx*dx + dy*dy);
    }
    
    public static double ptSegDistSq(double X1, double Y1, double X2, double Y2, double PX, double PY){
    	X2 -= X1;
    	Y2 -= Y1;
    	PX -= X1;
    	PY -= Y1;
    	double dotprod = PX * X2 + PY * Y2;
    	double projlenSq;
    	
    	if (dotprod <= 0.0) {
    		projlenSq = 0.0;
    	}else {
    		PX = X2 - PX;
    		PY = Y2 - PY;
    		dotprod = PX * X2 + PY * Y2;
    		if (dotprod <= 0.0) {
    			projlenSq = 0.0;
    		}else {
    			projlenSq = dotprod * dotprod / (X2 * X2 + Y2 * Y2);
    		}
    	}
    	
    	double lenSq = PX * PX + PY * PY - projlenSq;
    	if (lenSq < 0) {
    		lenSq = 0;
    	}

    	return lenSq;
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    private void showNotification(String note){
    	if(entered){
    		return;
    	}
    	Intent contentIntent = new Intent(this, GoogleMapsActivity.class);
/*    	Intent appIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.anddev.org"));
    	String tickerText = "Forget C Not";*/

    	Notification notification = new Notification(R.drawable.redmarker, "Notify", System.currentTimeMillis());
    	notification.setLatestEventInfo(GoogleMapsActivity.this, "Forget C Not",note, PendingIntent.getActivity(this.getBaseContext(), 0, contentIntent,
    			PendingIntent.FLAG_CANCEL_CURRENT));
    	mManager.notify(APP_ID, notification);
    }
    
    GpsStatus.Listener gpsListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            if( event == GpsStatus.GPS_EVENT_FIRST_FIX){
                System.out.println("GPS first fix");
                location_segment_number = 250;
            }
        }
 };

    
    public class GeoUpdateHandler implements LocationListener {
		public void onLocationChanged(Location location) {
			Location now = new Location("");
			now.setLatitude(me.getLatitudeE6()/ 1.0E6);
			now.setLongitude(me.getLongitudeE6()/ 1.0E6);
			rotatePointer(now.bearingTo(location));
//			caller();
//			Toast.makeText(getApplicationContext(), "Location changed..", Toast.LENGTH_SHORT).show();
//			System.out.println("On location change xlist[last]:"+xlist.get(xlist.size()-1));
			
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			me = new GeoPoint(lat, lng);
			
			if(autopan){
				mc.animateTo(me);
			}
			
	    	mapView.invalidate();

	    	if(!ready){
				return;
			}
			
			boolean tmp_inbounds = contains(lat, lng);
			System.out.println("Contains called.");
			if(inbounds != tmp_inbounds){
				msg_shown = false;
				inbounds = tmp_inbounds;
				if(!inbounds){
					c_boundary_paint.setColor(Color.RED);
					System.out.println("Before calcDist");
					calcDist();
					System.out.println("After calcDist");
					distance_label.setText(dist+" m");
					showNotification(dist+" m");
				}
				else{
					c_boundary_paint.setColor(Color.BLUE);
					distance_label.setText(label_text);
					showNotification(label_text);
					if(cc){
						entered = true;
						paymentMessage();
					}
				}
			}
			else{
				if(!inbounds){
					c_boundary_paint.setColor(Color.RED);
					System.out.println("Before calcDist");
					calcDist();
					System.out.println("After calcDist");
					if(dist < 1000){
						distance_label.setText(dist+" m");
						showNotification(dist+" m");
					}
					
					else{
						distance_label.setText("");
						showNotification("");
					}						
				}				
			}
		}

		public void onProviderDisabled(String provider) {
			System.out.println("Provider disabled");
		}

		public void onProviderEnabled(String provider) {
			System.out.println("Provider enabled");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

    public void getUrl(GeoPoint src, GeoPoint dest){
    	StringBuilder direction_url = new StringBuilder();
    	direction_url.append("http://maps.google.com/maps?f=d&hl=en");
    	direction_url.append("&saddr=");
    	direction_url.append(Double.toString((double) src.getLatitudeE6() / 1.0E6));
    	direction_url.append(",");
    	direction_url.append(Double.toString((double) src.getLongitudeE6() / 1.0E6));
    	direction_url.append("&daddr=");
    	direction_url.append(Double.toString((double) dest.getLatitudeE6() / 1.0E6));
    	direction_url.append(",");
    	direction_url.append(Double.toString((double) dest.getLongitudeE6() / 1.0E6));
    	direction_url.append("&ie=UTF8&0&om=0&output=kml");

    	Document doc = null;
    	HttpURLConnection urlConnection= null;
    	URL url = null;
    	
    	try{
    		url = new URL(direction_url.toString());

    		urlConnection=(HttpURLConnection)url.openConnection();
    		urlConnection.setRequestMethod("GET");
    		urlConnection.setDoOutput(true); 
    		urlConnection.setDoInput(true); 
    		urlConnection.connect();
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		doc = db.parse(urlConnection.getInputStream());
    		
    		if(doc.getElementsByTagName("GeometryCollection").getLength()>0){
    			String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
    			System.out.println("Path: "+path);
    			String[] pairs = path.split(" ");
    			drive_route = new GeoPoint[pairs.length];
    			for(int x = 0; x < pairs.length; x++){
    				String[] value_pair = pairs[x].split(",");
    				GeoPoint new_point = new GeoPoint((int)(Double.parseDouble(value_pair[1])*1E6),(int)(Double.parseDouble(value_pair[0])*1E6));
    				drive_route[x] = new_point;
    			}
   			}
    	}
    	catch(Exception ex){
    		System.out.println("Exception in the get URL: "+ex);
    	}
    }
    
    private void getLc(){
    	System.out.println("Downloading the lc_array.fcn ...");
    	DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://www.alliancerational.com/Script/lc_array.fcn");
        HttpResponse response = null;
        InputStream ii = null;
        try{
       	 response = httpClient.execute(request);
       	 ii = response.getEntity().getContent();
       	 ObjectInputStream ois = new ObjectInputStream(ii);
       	coordinate_segment_array = (int[][])ois.readObject();
       }
       catch(Exception e){
       	System.out.println("ImageFetcher Exception: "+e);
       }
    }
    
    private void setParkNoticeBoard(ParkSite park_site){
    	notice_layout.removeAllViews();
    	
    	TextView lots_heading = (TextView)View.inflate(this, R.layout.heading_textview, null);
    	lots_heading.setText("# of LOTS: "+park_site.getSpaces());
    	notice_layout.addView(lots_heading);
    	
    	TextView times = (TextView)View.inflate(this, R.layout.label_textview, null);
    	times.setText(park_site.getOpeningHours());
    	notice_layout.addView(times);
    	
    	TextView address_heading = (TextView)View.inflate(this, R.layout.heading_textview, null);
    	address_heading.setText("ADDRESS");
    	TextView address = (TextView)View.inflate(this, R.layout.label_textview, null);
    	address.setText(park_site.getAddress());
    	notice_layout.addView(address_heading);
    	notice_layout.addView(address);
    	
    	TextView charges_heading = (TextView)View.inflate(this, R.layout.heading_textview, null);
    	charges_heading.setText("CHARGES");
    	
    	notice_layout.addView(charges_heading);
    	for(int i = 0; i < park_site.getSlotLength(); i++){
    		TextView charges = (TextView)View.inflate(this, R.layout.label_textview, null);
        	charges.setText(park_site.hours[i]+" Hours: £"+park_site.charges[i]);
        	charges.setTextSize(12);
        	notice_layout.addView(charges);
    	}
    	
    	Button btn1 = (Button)slider.findViewById(R.id.slider_left);
    	Button btn2 = (Button)slider.findViewById(R.id.slider_right);
    	btn1.setEnabled(true);
    	btn2.setEnabled(true);
    	btn1.setText("Yes");
    	btn2.setText("No");

    	setParkSelectListener(btn1, park_site);
    	setParkCancelListener(btn2);
    }
    
    private void getParks(String url_string){
    	Document doc = null;
    	HttpURLConnection urlConnection= null;
    	try{
    		URL sourceUrl = new URL(url_string);
    		
    		urlConnection=(HttpURLConnection)sourceUrl.openConnection();
    		urlConnection.setRequestMethod("GET");
    		urlConnection.setDoOutput(true); 
    		urlConnection.setDoInput(true); 
    		urlConnection.connect();
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		doc = db.parse(urlConnection.getInputStream());
    		doc.getDocumentElement().normalize();

    		NodeList park_node_list = doc.getElementsByTagName("park_site");
    		
    		for(int i = 0; i < park_node_list.getLength(); i++){
    			Node node = park_node_list.item(i);
    			
    			if(node.getNodeType()== Node.ELEMENT_NODE){
    				Element element = (Element)node;
    				NodeList charge_slots = element.getElementsByTagName("charge_slots");
    				NodeList charge_slots_children = ((Element)charge_slots.item(0)).getElementsByTagName("charge_slot");
    				ArrayList<TimeSlot> time_slots_list = new ArrayList<TimeSlot>();
    				for(int x = 0; x < charge_slots_children.getLength(); x++){
    					Node slot_node = charge_slots_children.item(x);
    					if(slot_node.getNodeType()== Node.ELEMENT_NODE){
    						Element slot_element = (Element)slot_node;
    						time_slots_list.add(new TimeSlot(Integer.parseInt(getTagValue("hour", slot_element)), Double.parseDouble(getTagValue("charge", slot_element))));
    					}
    				}
    				try{
    					ParkSite new_site = new ParkSite(getTagValue("name", element), getTagValue("postcode", element), getTagValue("address", element), (int)(Double.parseDouble(getTagValue("latitude", element))*1.0E6), (int)(Double.parseDouble(getTagValue("longitude", element))*1.0E6), getTagValue("telephone", element), getTagValue("email", element), Integer.parseInt(getTagValue("spaces", element)), getTagValue("icon", element), getTagValue("opening", element)+" till "+getTagValue("closing", element), time_slots_list);
        				park_list.add(new_site);
    				}
    				catch(Exception ex1){
    					System.out.println("Could not enter a park site. :"+ex1);
    				}
    			}
    		}
    	}
    	catch(Exception ex){
    		System.out.println("Exception caught in the getParks: "+ex);
    	}
    }

    private String getGreeting(int id){
    	HttpClient client = new DefaultHttpClient();
    	HttpPost post = new HttpPost("http://www.alliancerational.com/Script/greetingserver.php");
    	String result = "";

    	try {
    		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("versionid", ""+id));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
			HttpResponse response = client.execute(post);
			InputStreamReader in = new InputStreamReader(response.getEntity().getContent());

			StringBuffer xml = new StringBuffer();
			int c =0;
			while( (c = in.read()) != -1){
                xml.append((char)c);
             }
			result = xml.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    private int setGreeting(String source){
    	Document doc = null;
    	int live = 0;
    	try{
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    		DocumentBuilder db = dbf.newDocumentBuilder();
    		ByteArrayInputStream stream = new ByteArrayInputStream(source.getBytes());

    		doc = db.parse(stream);
    		doc.getDocumentElement().normalize();

    		NodeList park_node_list = doc.getElementsByTagName("greeting");
    		
    		for(int i = 0; i < park_node_list.getLength(); i++){
    			Node node = park_node_list.item(i);
    			
    			if(node.getNodeType()== Node.ELEMENT_NODE){
    				Element element = (Element)node;
    				try{
    					greetingheading = getTagValue("heading", element);
    					greetingtext = getTagValue("text", element);
    					greetingbuttontext = getTagValue("button_text", element);
    					live = Integer.parseInt(getTagValue("live", element));
    				}
    				catch(Exception ex1){
    					System.out.println("Could not get the greeting. :"+ex1);
    				}
    			}
    		}
    	}
    	catch(Exception ex){
    		System.out.println("Exception caught in the getGreeting: "+ex);
    	}
    	return live;
    }

    private static String getTagValue(String sTag, Element eElement) {
    	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
    	Node nValue = (Node) nlList.item(0);
    	return nValue.getNodeValue();
    	}
    
    private String postUserStat(String name, double latitude, double longitude){
    	HttpClient client = new DefaultHttpClient();
    	HttpPost post = new HttpPost("http://www.alliancerational.com/Script/fcn_android_user_stats.php");
    	String buffer, result = "";

    	try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("name", name));
			nameValuePairs.add(new BasicNameValuePair("latitude", ""+latitude));
			nameValuePairs.add(new BasicNameValuePair("longitude", ""+longitude));
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			
			while ((buffer = rd.readLine()) != null){
				result = result + buffer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
    }
    
    private void createGpsDisabledAlert(){
    	notice_layout.removeAllViews();
    	TextView heading_view  = (TextView)View.inflate(this, R.layout.heading_textview, null);
    	heading_view.setText("GPS Disabled");
    	notice_layout.addView(heading_view);
    	
    	TextView text_view  = (TextView)View.inflate(this, R.layout.label_textview, null);
    	text_view.setText("ForgetCNot needs GPS to be enabled. Would you like to enable GPS now?");
    	notice_layout.addView(text_view);
    	
    	Button btn1 = (Button)findViewById(R.id.slider_left);
    	btn1.setText("Yes");
    	Button btn2 = (Button)findViewById(R.id.slider_right);
    	btn2.setText("Exit");
    	setGPSListener(btn1);
    	setExitListener(btn2);
    	slider.setLayoutAnimEntrance(slider, slider.getContext());
    }
    
    private void setMessageSlider(String heading, String text, String left, String right){
    	notice_layout.removeAllViews();
    	TextView heading_view  = (TextView)View.inflate(this, R.layout.heading_textview, null);
    	heading_view.setText(heading);
    	notice_layout.addView(heading_view);
    	
    	TextView text_view  = (TextView)View.inflate(this, R.layout.label_textview, null);
    	text_view.setText(text);
    	notice_layout.addView(text_view);
    	
    	Button btn1 = (Button)findViewById(R.id.slider_left);
    	btn1.setText(left);
    	Button btn2 = (Button)findViewById(R.id.slider_right);
    	btn2.setText("Exit");
    	btn2.setEnabled(true);
    	setExitListener(btn2);

    	if(live !=1){
    		System.out.println("Exit Listener for btn1");
    	}
    	else{
    		System.out.println("Slider Exit for btn1");
    		setSliderExitListener(btn1);
    	}
    }
    
    private void call(String number) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(number));
            startActivity(callIntent);
        } catch (Exception e) {
            System.out.println("Call failed: "+e);
        }
    }

}