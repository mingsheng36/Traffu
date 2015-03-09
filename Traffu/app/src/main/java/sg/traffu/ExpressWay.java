package sg.traffu;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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

import com.jjoe64.graphview.CustomLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class ExpressWay extends Activity {

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
		String expway = getIntent().getExtras().getString("express_way");

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
			tv2.setText(R.string.kpe_punggol);
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
			tv2.setText(R.string.mce_tr);
		}
		else if (expway.equals("aye")) {
			tv1.setText(R.string.aye_city);
			tv2.setText(R.string.aye_tuas);
		}
		else if (expway.equals("kje")) {
			tv1.setText(R.string.kje_bp);
			tv2.setText(R.string.kje_jurong);
		}
		else if (expway.equals("bke")) {
			tv1.setText(R.string.bke_bt);
			tv2.setText(R.string.bke_woodlands);
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
					} else if (value >= 5 && value < 6) {
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
					}else {				
						return null;
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
				Log.e(Double.toString(value), Boolean.toString(isValueX));
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
		//new AsyncTaskParseJson().execute();

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
				Log.e("Error", e.getMessage());
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

		String yourJsonStringUrl = "https://www.kimonolabs.com/api/4nmcwceg?apikey=cQOzDxTQLTsYstrAjZEDQAEl57Og8TjH";
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
					String point = c.getString("point");
					JSONObject img = c.getJSONObject("img");
					String url = img.getString("src");

					for (int j = 0; j < upper_cams.length; j++) {
						if (Integer.parseInt(upper_cams[j]) == i+1) {
							Log.e("sss", point + " " + img);
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
					String point = c.getString("point");
					JSONObject img = c.getJSONObject("img");
					String url = img.getString("src");
					
					for (int k = 0; k < lower_cams.length; k++) {
						
						Log.e("final", i+1 + " " + Integer.parseInt(lower_cams[k]));
						if (Integer.parseInt(lower_cams[k]) == i) {
							Log.e("sss", point + " " + img);
							
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

}


