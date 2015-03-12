package sg.traffu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class ExpressWay extends Activity {

    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    String serverIP = "http://traffu-mingsheng36.rhcloud.com";
    //String serverIP = "http://192.168.1.149:8000";

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "680851557511";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "Traffu";

    GoogleCloudMessaging gcm;
    Context context;

    String regid;
    String expway;

	String[] upper_exits;
	String[] lower_exits;

	String[] upper_cams;
	String[] lower_cams;

	GraphViewSeries upper_series;
	GraphViewSeries lower_series;

	LinearLayout upper_images; 
	LinearLayout lower_images;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expway);
		expway = getIntent().getExtras().getString("express_way");

        // Check device for Play Services APK. If check succeeds, proceed with
        //  GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            context = getApplicationContext();
            regid = getRegistrationId(context);
            registerInBackground();
            Toast.makeText(getApplicationContext(), "You will receive notifications on " + expway.toUpperCase(), Toast.LENGTH_SHORT).show();
        }

		TextView tv1 = (TextView) findViewById(R.id.tv1);
		TextView tv2 = (TextView) findViewById(R.id.tv2);

		//set the style for the timeline
		GraphViewSeriesStyle green = new GraphViewSeriesStyle(Color.GREEN, 70);
		GraphViewSeriesStyle red = new GraphViewSeriesStyle(Color.RED, 70);
		GraphViewSeriesStyle orange = new GraphViewSeriesStyle(Color.rgb(243, 167, 50), 70);

		Resources res = getResources();

		// set the exits for the timeline
		if (expway.equals("cte")) {
	
			tv1.setText(R.string.cte_city);
			upper_exits = res.getStringArray(R.array.cte_city);
			upper_cams = res.getStringArray(R.array.cte_city_cam);
			GraphViewData[] cte_city = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1),
					new GraphViewData(10,1),
					new GraphViewData(11,1),
					new GraphViewData(12,1)		
			};

			tv2.setText(R.string.cte_seletar);
			lower_exits = res.getStringArray(R.array.cte_seletar);
			lower_cams = res.getStringArray(R.array.cte_seletar_cam);
			GraphViewData[] cte_seletar = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1),
					new GraphViewData(10,1),
					new GraphViewData(11,1)
			};
			
			upper_series = new GraphViewSeries("", green, cte_city);
			lower_series = new GraphViewSeries("", green, cte_seletar);
			
		} 
		else if (expway.equals("tpe")) {

			tv1.setText(R.string.tpe_changi);
			upper_exits = res.getStringArray(R.array.tpe_changi);
			upper_cams = res.getStringArray(R.array.tpe_changi_cam);
			GraphViewData[] tpe_changi = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1)
			};

			tv2.setText(R.string.tpe_seletar);
			lower_exits = res.getStringArray(R.array.tpe_seletar);
			lower_cams = res.getStringArray(R.array.tpe_seletar_cam);
			GraphViewData[] tpe_seletar = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1)
			};

			upper_series = new GraphViewSeries("", green, tpe_changi);
			lower_series = new GraphViewSeries("", green, tpe_seletar);

		}
		else if (expway.equals("kpe")) {
			tv1.setText(R.string.kpe_city);
            upper_exits = res.getStringArray(R.array.sle_seletar);
            upper_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] kpe_upper = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
			tv2.setText(R.string.kpe_punggol);
            lower_exits = res.getStringArray(R.array.sle_seletar);
            lower_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] kpe_lower = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
            upper_series = new GraphViewSeries("", green, kpe_upper);
            lower_series = new GraphViewSeries("", green, kpe_lower);
		}
		else if (expway.equals("pie")) {
			
			tv1.setText(R.string.pie_changi);
			upper_exits = res.getStringArray(R.array.pie_changi);
			upper_cams = res.getStringArray(R.array.pie_changi_cam);
			GraphViewData[] pie_changi = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1),
					new GraphViewData(10,1),
					new GraphViewData(11,1),
					new GraphViewData(12,1),
					new GraphViewData(13,1),
					new GraphViewData(14,1),
					new GraphViewData(15,1),
					new GraphViewData(16,1),
					new GraphViewData(17,1),
					new GraphViewData(18,1),
					new GraphViewData(19,1),
					new GraphViewData(20,1),
					new GraphViewData(21,1),
					new GraphViewData(22,1),
					new GraphViewData(23,1),
					new GraphViewData(24,1),
					new GraphViewData(25,1),
					new GraphViewData(26,1)
			};

			tv2.setText(R.string.pie_tuas);
			lower_exits = res.getStringArray(R.array.pie_tuas);
			lower_cams = res.getStringArray(R.array.pie_tuas_cam);
			GraphViewData[] pie_tuas = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1),
					new GraphViewData(10,1),
					new GraphViewData(11,1),
					new GraphViewData(12,1),
					new GraphViewData(13,1),
					new GraphViewData(14,1),
					new GraphViewData(15,1),
					new GraphViewData(16,1),
					new GraphViewData(17,1),
					new GraphViewData(18,1),
					new GraphViewData(19,1),
					new GraphViewData(20,1),
					new GraphViewData(21,1),
					new GraphViewData(22,1),
					new GraphViewData(23,1),
					new GraphViewData(24,1),
					new GraphViewData(25,1),
					new GraphViewData(26,1)
			};
			
			upper_series = new GraphViewSeries("", green, pie_changi);
			lower_series = new GraphViewSeries("", green, pie_tuas);
			
		}
		else if (expway.equals("ecp")) {

			tv1.setText(R.string.ecp_changi);
			upper_exits = res.getStringArray(R.array.ecp_changi);
			upper_cams = res.getStringArray(R.array.ecp_changi_cam);
			GraphViewData[] ecp_changi = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
					new GraphViewData(9,1),
					new GraphViewData(10,1),
					new GraphViewData(11,1)		
			};

			tv2.setText(R.string.ecp_city);
			lower_exits = res.getStringArray(R.array.ecp_city);
			lower_cams = res.getStringArray(R.array.ecp_city_cam);
			GraphViewData[] ecp_city = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1)
			};
			
			upper_series = new GraphViewSeries("", green, ecp_changi);
			lower_series = new GraphViewSeries("", green, ecp_city);
			
		}
		else if (expway.equals("mce")) {
			tv1.setText(R.string.mce_mb);
            upper_exits = res.getStringArray(R.array.sle_seletar);
            upper_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] mce_upper = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1),
                    new GraphViewData(6,1),
                    new GraphViewData(7,1),
                    new GraphViewData(8,1),

            };
			tv2.setText(R.string.mce_tr);
            lower_exits = res.getStringArray(R.array.sle_seletar);
            lower_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] mce_lower = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1),
                    new GraphViewData(6,1),
                    new GraphViewData(7,1),
                    new GraphViewData(8,1),
            };
            upper_series = new GraphViewSeries("", green, mce_upper);
            lower_series = new GraphViewSeries("", green, mce_lower);
		}
		else if (expway.equals("aye")) {
			tv1.setText(R.string.aye_city);
            upper_exits = res.getStringArray(R.array.sle_seletar);
            upper_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] aye_upper = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
			tv2.setText(R.string.aye_tuas);
            lower_exits = res.getStringArray(R.array.sle_seletar);
            lower_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] aye_lower = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
            upper_series = new GraphViewSeries("", green, aye_upper);
            lower_series = new GraphViewSeries("", green, aye_lower);
		}
		else if (expway.equals("kje")) {
			tv1.setText(R.string.kje_bp);
            upper_exits = res.getStringArray(R.array.sle_seletar);
            upper_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] kje_upper = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
			tv2.setText(R.string.kje_jurong);
            lower_exits = res.getStringArray(R.array.sle_seletar);
            lower_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] kje_lower = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
            upper_series = new GraphViewSeries("", green, kje_upper);
            lower_series = new GraphViewSeries("", green, kje_lower);
		}
		else if (expway.equals("bke")) {
			tv1.setText(R.string.bke_bt);
            upper_exits = res.getStringArray(R.array.sle_seletar);
            upper_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] bke_upper = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
			tv2.setText(R.string.bke_woodlands);
            lower_exits = res.getStringArray(R.array.sle_seletar);
            lower_cams = res.getStringArray(R.array.sle_seletar_cam);
            GraphViewData[] bke_lower = {
                    new GraphViewData(1,1),
                    new GraphViewData(2,1),
                    new GraphViewData(3,1),
                    new GraphViewData(4,1),
                    new GraphViewData(5,1)
            };
            upper_series = new GraphViewSeries("", green, bke_upper);
            lower_series = new GraphViewSeries("", green, bke_lower);
		}
		else if (expway.equals("sle")) {
	
			tv1.setText(R.string.sle_seletar);
			upper_exits = res.getStringArray(R.array.sle_seletar);
			upper_cams = res.getStringArray(R.array.sle_seletar_cam);
			GraphViewData[] sle_seletar = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
					new GraphViewData(7,1),
					new GraphViewData(8,1),
			};

			tv2.setText(R.string.sle_woodlands);
			lower_exits = res.getStringArray(R.array.sle_woodlands);
			lower_cams = res.getStringArray(R.array.sle_woodlands_cam);
			GraphViewData[] sle_woodlands = {
					new GraphViewData(1,1),
					new GraphViewData(2,1),
					new GraphViewData(3,1),
					new GraphViewData(4,1),
					new GraphViewData(5,1),
					new GraphViewData(6,1),
			};

			upper_series = new GraphViewSeries("", green, sle_seletar);
			lower_series = new GraphViewSeries("", green, sle_woodlands);
		}

		/***************************************************/
		// TRAFFIC NEWS DATAS
		GraphViewSeries jam2 = new GraphViewSeries("", red, new GraphViewData[] {
				new GraphViewData(5, 1.0d)
				, new GraphViewData(6, 1.0d)		
		});

		GraphViewSeries jam1 = new GraphViewSeries("", orange, new GraphViewData[] {
				new GraphViewData(2, 1.0d)
				, new GraphViewData(3, 1.0d)		
		});

		/************** UPPER GRAPH ******************/
		GraphView time1 = new LineGraphView(this, "Upper");
		time1.setTitle("");
		time1.setViewPort(1, 5);
		time1.setScrollable(true);
		time1.setScalable(true);
		time1.getGraphViewStyle().setGridColor(Color.BLACK);
		time1.getGraphViewStyle().setTextSize(22);
		time1.getGraphViewStyle().setNumHorizontalLabels(3);
		time1.setShowVerticalLabels(false);
		time1.addSeries(upper_series); 
		time1.addSeries(jam1);
		time1.addSeries(jam2);
		time1.setCustomLabelFormatter(new CustomLabelFormatter() {
			@Override
			public String formatLabel(double value, boolean isValueX) {
				if (isValueX) {
					if (value >= 1 && value < 2) {
						return upper_exits[0];
					} else if (value >= 2 && value < 3) {
						return upper_exits[1];
					} else if (value >= 3 && value < 4) {
						return upper_exits[2];
					} else if (value >= 4 && value < 5) {
						return upper_exits[3];
					} else {
                        if (value >= 5 && value < 6) {
                            return upper_exits[4];
                        } else if (value >= 6 && value < 7) {
                            return upper_exits[5];
                        } else if (value >= 7 && value < 8) {
                            return upper_exits[6];
                        } else if (value >= 8 && value < 9) {
                            return upper_exits[7];
                        } else if (value >= 9 && value < 10) {
                            return upper_exits[8];
                        } else if (value >= 10 && value < 11) {
                            return upper_exits[9];
                        } else if (value >= 11 && value < 12) {
                            return upper_exits[10];
                        } else if (value >= 12 && value < 13) {
                            return upper_exits[11];
                        } else if (value >= 13 && value < 14) {
                            return upper_exits[12];
                        } else if (value >= 14 && value < 15) {
                            return upper_exits[13];
                        } else if (value >= 15 && value < 16) {
                            return upper_exits[14];
                        } else if (value >= 16 && value < 17) {
                            return upper_exits[15];
                        } else if (value >= 17 && value < 18) {
                            return upper_exits[16];
                        } else if (value >= 18 && value < 19) {
                            return upper_exits[17];
                        } else if (value >= 19 && value < 20) {
                            return upper_exits[18];
                        } else if (value >= 20 && value < 21) {
                            return upper_exits[19];
                        } else if (value >= 21 && value < 22) {
                            return upper_exits[20];
                        } else if (value >= 22 && value < 23) {
                            return upper_exits[21];
                        } else if (value >= 23 && value < 24) {
                            return upper_exits[22];
                        } else if (value >= 24 && value < 25) {
                            return upper_exits[23];
                        } else if (value >= 25 && value < 26) {
                            return upper_exits[24];
                        } else if (value >= 26 && value < 27) {
                            return upper_exits[25];
                        } else if (value >= 27 && value < 28) {
                            return upper_exits[26];
                        } else if (value >= 28 && value < 29) {
                            return upper_exits[27];
                        } else if (value >= 29 && value < 30) {
                            return upper_exits[28];
                        } else if (value == 30) {
                            return upper_exits[29];
                        } else {
                            return null;
                        }
                    }
				}
				return null;
			}
		});			
		LinearLayout upper = (LinearLayout) findViewById(R.id.time1);
		upper.addView(time1);


		/************** LOWER GRAPH ******************/
		GraphView time2 = new LineGraphView(this, "lower");
		time2.setTitle("");
		time2.setViewPort(1, 5);
		time2.setScrollable(true);
		time2.setScalable(true);
		time2.getGraphViewStyle().setGridColor(Color.BLACK);
		time2.getGraphViewStyle().setTextSize(22);
		time2.getGraphViewStyle().setNumHorizontalLabels(3);
		time2.setShowVerticalLabels(false);
		time2.addSeries(lower_series); 
		time2.addSeries(jam1);
		time2.addSeries(jam2);
		time2.setCustomLabelFormatter(new CustomLabelFormatter() {
			@Override
			public String formatLabel(double value, boolean isValueX) {
				if (isValueX) {
					if (value >= 1 && value < 2) {
						return lower_exits[0];
					} else if (value >= 2 && value < 3) {
						return lower_exits[1];
					} else if (value >= 3 && value < 4) {
						return lower_exits[2];
					} else if (value >= 4 && value < 5) {
						return lower_exits[3];
					} else if (value >= 5 && value < 6) {
						return lower_exits[4];
					} else if (value >= 6 && value < 7) {
						return lower_exits[5];
					} else if (value >= 7 && value < 8) {
						return lower_exits[6];
					} else if (value >= 8 && value < 9) {
						return lower_exits[7];
					} else if (value >= 9 && value < 10) {
						return lower_exits[8];
					} else if (value >= 10 && value < 11) {
						return lower_exits[9];
					} else if (value >= 11 && value < 12) {
						return lower_exits[10];
					} else if (value >= 12 && value < 13) {
						return lower_exits[11];
					} else if (value >= 13 && value < 14) {
						return lower_exits[12];
					} else if (value >= 14 && value < 15) {
						return lower_exits[13];
					} else if (value >= 15 && value < 16) {
						return lower_exits[14];
					} else if (value >= 16 && value < 17) {
						return lower_exits[15];
					} else if (value >= 17 && value < 18) {
						return lower_exits[16];
					} else if (value >= 18 && value < 19) {
						return lower_exits[17];
					} else if (value >= 19 && value < 20) {
						return lower_exits[18];
					} else if (value >= 20 && value < 21) {
						return lower_exits[19];
					} else if (value >= 21 && value < 22) {
						return lower_exits[20];
					} else if (value >= 22 && value < 23) {
						return lower_exits[21];
					} else if (value >= 23 && value < 24) {
						return lower_exits[22];
					} else if (value >= 24 && value < 25) {
						return lower_exits[23];
					} else if (value >= 25 && value < 26) {
						return lower_exits[24];
					} else if (value >= 26 && value < 27) {
						return lower_exits[25];
					} else if (value >= 27 && value < 28) {
						return lower_exits[26];
					} else if (value >= 28 && value < 29) {
						return lower_exits[27];
					} else if (value >= 29 && value < 30) {
						return lower_exits[28];
					} else if (value == 30) {
						return lower_exits[29];
					}else {				
						return null;
					}
				}
				return null;
			}
		});
		LinearLayout lower = (LinearLayout) findViewById(R.id.time2);
		lower.addView(time2);

		/***************************************************/

		upper_images = (LinearLayout) findViewById(R.id.upper_images);
		lower_images = (LinearLayout) findViewById(R.id.lower_images);

		//json load images
		new AsyncTaskParseJson().execute();

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;
		String position;

		public DownloadImageTask(ImageView bmImage, String position) {
			this.bmImage = bmImage;
			this.position = position;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
			if (position.equals("upper")) {
				upper_images.addView(bmImage);
			} else if (position.equals("lower")){
				lower_images.addView(bmImage);
			}	
		}
	}

	private class AsyncTaskParseJson extends AsyncTask<String, String, JSONArray> {

		String yourJsonStringUrl = "https://www.kimonolabs.com/api/6ait7nxe?apikey=cQOzDxTQLTsYstrAjZEDQAEl57Og8TjH";
		JSONArray dataJsonArr = null;
		@Override
		protected JSONArray doInBackground(String... arg0) {

			try {
				JsonParser jParser = new JsonParser();
				JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);

				dataJsonArr = json.getJSONObject("results").getJSONArray("collection1");

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return dataJsonArr;
		}

		@Override
		protected void onPostExecute(JSONArray strFromDoInBg) {

			ImageView[] upper_image_array = new ImageView[100];
			ImageView[] lower_image_array = new ImageView[100];
			
			
			try {
				// loop through all users
				for (int i = 0; i < dataJsonArr.length(); i++) {

					JSONObject c = dataJsonArr.getJSONObject(i);
                    JSONObject img = c.getJSONObject("img");
					String url = img.getString("src");

                    for (String upper_cam : upper_cams) {
                        if (Integer.parseInt(upper_cam) == i + 1) {
                            upper_image_array[i] = new ImageView(getApplicationContext());
                            upper_image_array[i].setLayoutParams(new LayoutParams(
                                    400,
                                    LayoutParams.MATCH_PARENT));
                            upper_image_array[i].setScaleType(ScaleType.FIT_XY);
                            new DownloadImageTask(upper_image_array[i], "upper").execute(url);
                        }
                    }

				}

				for (int i = dataJsonArr.length(); i > 0; i--) {
					
					JSONObject c = dataJsonArr.getJSONObject(i-1);
                    JSONObject img = c.getJSONObject("img");
					String url = img.getString("src");

                    for (String lower_cam : lower_cams) {
                        if (Integer.parseInt(lower_cam) == i) {
                            lower_image_array[i] = new ImageView(getApplicationContext());
                            lower_image_array[i].setLayoutParams(new LayoutParams(
                                    400,
                                    LayoutParams.MATCH_PARENT));
                            lower_image_array[i].setScaleType(ScaleType.FIT_XY);
                            new DownloadImageTask(lower_image_array[i], "lower").execute(url);
                        }
                    }
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

    // You need to do the Play Services APK check here too.
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
        updateInBackground("online");
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Gets the current registration ID for application on GCM service.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing registration ID is not guaranteed to work with
        // the new app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGCMPreferences() {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the registration ID in your app is up to you.
        return getSharedPreferences(ExpressWay.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String msg;
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.
                    // The request to your server should be authenticated if your app
                    // is using accounts.
                    sendOnline();

                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the
                    // message using the 'from' address in the message.

                    // Persist the registration ID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

        }.execute(null, null, null);

    }


    /**
     * Stores the registration ID and app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.apply();
    }

    private void sendOnline() {
        try {
            // Create http client object to send request to server
            HttpClient client = new DefaultHttpClient();
            // Create URL string
            String URL = serverIP + "/update?rid=" + regid + "&state=online&expway=" + expway;
            // Create Request to server and get response
            HttpGet httpget= new HttpGet();
            httpget.setURI(new URI(URL));
            client.execute(httpget);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateInBackground(final String status) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String msg;
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.
                    // The request to your server should be authenticated if your app
                    // is using accounts.
                    if (status.equals("online")) {
                        sendOnline();
                    } else if (status.equals("offline")) {
                        sendOffline();
                    }
                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the
                    // message using the 'from' address in the message.

                    // Persist the registration ID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

        }.execute(null, null, null);

    }

    private void sendOffline() {
        try {
            // Create http client object to send request to server
            HttpClient client = new DefaultHttpClient();
            // Create URL string
            String URL = serverIP + "/update?rid=" + regid + "&state=offline&expway=null";
            // Create Request to server and get response
            HttpGet httpget= new HttpGet();
            httpget.setURI(new URI(URL));
            client.execute(httpget);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}