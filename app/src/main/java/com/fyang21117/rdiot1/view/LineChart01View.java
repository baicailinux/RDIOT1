package com.fyang21117.rdiot1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.fyang21117.rdiot1.view.chart.LineChart;
import com.fyang21117.rdiot1.view.chart.LineData;
import com.fyang21117.rdiot1.view.event.click.PointPosition;
import com.fyang21117.rdiot1.view.renderer.XEnum;

import java.util.LinkedList;

public class LineChart01View extends DemoView {
	//折线图（封闭式）
	private String TAG = "LineChart01View";
	private LineChart chart = new LineChart();
	
	//标签集合
	private LinkedList<String> labels = new LinkedList<>();//数据标签
	private LinkedList<LineData> chartData = new LinkedList<>();//数据
	private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);//图片线条（通用）的抗锯齿需要另外设置：

	public LineChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}
	
	public LineChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        initView();
	 }
	 
	 public LineChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView() {
		 	chartLabels();
			chartDataSet();	
			chartRender();
			
			//綁定手势滑动事件
			this.bindTouch(this,chart);
	 }

	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  
	
	private void chartRender()
	{
		try {				
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);	
			
			//限制Tickmarks可滑动偏移范围
			chart.setXTickMarksOffsetMargin(ltrb[2] - 20.f);
			chart.setYTickMarksOffsetMargin(ltrb[3] - 20.f);

			//显示边框
			chart.showRoundBorder();
			
			//设定数据源
			chart.setCategories(labels);								
			chart.setDataSource(chartData);
			
			//数据轴最大值
			chart.getDataAxis().setAxisMax(100); 
			//数据轴刻度间隔
			chart.getDataAxis().setAxisSteps(10);
			
			//背景网格
			chart.getPlotGrid().showHorizontalLines();
			//chart.getPlotGrid().showVerticalLines();
            //显示偶数行背景色
			chart.getPlotGrid().showEvenRowBgColor();
			//显示奇数行背景色
			chart.getPlotGrid().showOddRowBgColor();

			//触摸宽度
			chart.getPlotGrid().getHorizontalLinePaint().setStrokeWidth(2);
			//设置横向网格线当前绘制风格
			chart.getPlotGrid().setHorizontalLineStyle(XEnum.LineStyle.DASH);
			//设置竖向网格线当前绘制风格
			chart.getPlotGrid().setVerticalLineStyle(XEnum.LineStyle.DOT);
			
			chart.getPlotGrid().getHorizontalLinePaint().setColor(Color.RED);//水平红色线
			chart.getPlotGrid().getVerticalLinePaint().setColor(Color.BLUE);//垂直蓝色线
			
			chart.setTitle("折线图(Line Chart)");
			chart.addSubtitle("(2018.11.22)");
			chart.getAxisTitle().setLowerTitle("(time)");
			
			//激活点击监听
			chart.ActiveListenItemClick();
			//为了让触发更灵敏，可以扩大5px的点击监听范围
			chart.extPointClickRange(5);
			chart.showClikedFocus();
												
			//绘制十字交叉线
			chart.showDyLine();
			chart.getDyLine().setDyLineStyle(XEnum.DyLineStyle.Vertical);


			/*			
			//想隐藏轴的可以下面的函数来隐藏
			chart.getDataAxis().hide();
			chart.getCategoryAxis().hide();
			//想设置刻度线属性的可用下面函数
			chart.getDataAxis().getTickMarksPaint()
			chart.getCategoryAxis().getTickMarksPaint()
			//想设置刻度线标签属性的可用下面函数 
			chart.getDataAxis().getAxisTickLabelPaint()	
			chart.getCategoryAxis().getAxisTickLabelPaint()
			*/

            //增加绘图区域宽度
            //chart.getPlotArea().extWidth(20f);
			chart.getPlotArea().extWidth(100.f);
			
			//调整轴显示位置
			chart.setDataAxisLocation(XEnum.AxisLocation.LEFT);
			chart.setCategoryAxisLocation(XEnum.AxisLocation.BOTTOM);
			
			//收缩绘图区右边分割的范围，让绘图区的线不显示出来
			chart.getClipExt().setExtRight(0.f);
			
			
			//test x坐标从刻度线而不是轴开始
			//chart.setXCoordFirstTickmarksBegin(true);
			//chart.getCategoryAxis().showTickMarks();
			//chart.getCategoryAxis().setVerticalTickPosition(XEnum.VerticalAlign.MIDDLE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.toString());
		}
	}
	private void chartDataSet()
	{
		//Line 1
		LinkedList<Double> dataSeries1= new LinkedList<>();
        dataSeries1.add(05d);
        dataSeries1.add(09d);
        dataSeries1.add(11d);
        dataSeries1.add(17d);
        dataSeries1.add(20d);
        dataSeries1.add(05d);
        dataSeries1.add(09d);
        dataSeries1.add(11d);
        dataSeries1.add(17d);
        dataSeries1.add(20d);
        //LineData（键值，数据集，线条颜色）
		LineData lineData1 = new LineData("TGS2600",dataSeries1,Color.rgb(234, 83, 71));
		//lineData1.setLabelVisible(true);		                //显示标签
		lineData1.setDotStyle(XEnum.DotStyle.RECT);	            //数据点：方形
		lineData1.getDotLabelPaint().setColor(Color.BLUE);      //标签字体蓝色
		lineData1.getDotLabelPaint().setTextSize(22);           //标签字体大小
		lineData1.getDotLabelPaint().setTextAlign(Align.LEFT);  //标签文字对齐
		lineData1.setItemLabelRotateAngle(45.f);                //设置标签在显示时的旋转角度
        lineData1.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.TEXT);//标签标注框形状
		//lineData1.getLabelOptions().
		//lineData1.setDataSet(dataSeries);
		//this.invalidate();
		
		//Line 2
		LinkedList<Double> dataSeries2= new LinkedList<>();
        dataSeries2.add(22d);
        dataSeries2.add(25d);
        dataSeries2.add(29d);
        dataSeries2.add(34d);
        dataSeries2.add(40d);
        dataSeries2.add(22d);
        dataSeries2.add(25d);
        dataSeries2.add(29d);
        dataSeries2.add(34d);
        dataSeries2.add(40d);
		LineData lineData2 = new LineData("TGS2603",dataSeries2,Color.rgb(75, 166, 51));
       // lineData2.setLabelVisible(true);
        lineData2.setDotStyle(XEnum.DotStyle.RING);                 //圆环状
        //lineData1.getDotLabelPaint().setColor(Color.BLUE);          //标签字体蓝色
		//lineData2.getPlotLine().getDotPaint().setColor(Color.RED);  //圆环颜色
		//lineData2.getPlotLine().getPlotDot().setRingInnerColor(Color.GREEN);//圆环填充颜色
        //lineData1.getDotLabelPaint().setTextSize(22);               //字体大小
       // lineData1.getDotLabelPaint().setTextAlign(Align.LEFT);

        lineData1.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.TEXT);//标签标注框形状
        lineData1.setItemLabelRotateAngle(45.f);                    //设置标签在显示时的旋转角度
		//lineData2.setLineStyle(XEnum.LineStyle.DASH);             //线条风格
		//lineData2.getPlotLine().getPlotDot().setDotRadius(radius);// 设置点大小
						
		//Line 3
		LinkedList<Double> dataSeries3= new LinkedList<>();
        dataSeries3.add(40d);
        dataSeries3.add(42d);
        dataSeries3.add(45d);
        dataSeries3.add(49d);
        dataSeries3.add(50d);
        dataSeries3.add(40d);
        dataSeries3.add(42d);
        dataSeries3.add(45d);
        dataSeries3.add(49d);
        dataSeries3.add(50d);
		LineData lineData3 = new LineData("CO",dataSeries3,Color.rgb(123, 89, 168));
       // lineData2.setLabelVisible(true);
		lineData3.setDotStyle(XEnum.DotStyle.DOT);
        lineData1.getDotLabelPaint().setColor(Color.BLUE);          //标签字体蓝色
        lineData1.getDotLabelPaint().setTextSize(22);               //字体大小
        lineData1.getDotLabelPaint().setTextAlign(Align.LEFT);
        lineData3.setDotRadius(10);             // 开放交叉点的半径,用来决定绘制的点的图形的大小

        lineData1.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.TEXT);//标签标注框形状
        lineData1.setItemLabelRotateAngle(45.f);//设置标签在显示时的旋转角度
		//lineData3.setLabelVisible(true);
		//lineData3.getDotLabelPaint().setTextAlign(Align.CENTER);				



		//Line 4
		LinkedList<Double> dataSeries4= new LinkedList<>();
        dataSeries4.add(50d);
        dataSeries4.add(55d);
        dataSeries4.add(59d);
        dataSeries4.add(64d);
        dataSeries4.add(68d);
        dataSeries4.add(50d);
        dataSeries4.add(55d);
        dataSeries4.add(59d);
        dataSeries4.add(64d);
        dataSeries4.add(68d);
		LineData lineData4 = new LineData("VOC",dataSeries4,Color.rgb(84, 206, 231));
       // lineData4.setLabelVisible(true);
        lineData4.setDotStyle(XEnum.DotStyle.PRISMATIC);
		lineData4.getLinePaint().setStrokeWidth(2);                  //把线弄细点
        lineData1.getDotLabelPaint().setColor(Color.BLUE);          //标签字体蓝色
        lineData1.getDotLabelPaint().setTextSize(22);               //字体大小
        lineData1.getDotLabelPaint().setTextAlign(Align.LEFT);
        lineData1.setItemLabelRotateAngle(45.f);                    //设置标签在显示时的旋转角度
        lineData1.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.TEXT);//标签标注框形状
		//lineData4.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.CIRCLE);
		//lineData4.getLabelOptions().getBox().getBackgroundPaint().setColor(Color.GREEN);

		/*
		//Line 5
		LinkedList<Double> valuesE= new LinkedList<>();
        valuesE.add(0d);
        valuesE.add(20d);
        valuesE.add(45d);
        valuesE.add(90d);
		LineData lineData5 = new LineData("定制",valuesE,Color.rgb(234, 142, 43));
		lineData5.setDotRadius(15);
		lineData5.setDotStyle(XEnum.DotStyle.TRIANGLE);
				
		//Line 2
		LinkedList<Double> dataSeries6= new LinkedList<>();
		dataSeries6.add((double)2);
		dataSeries6.add((double)5);
		dataSeries6.add((double)9);
		dataSeries6.add((double)4);
		dataSeries6.add((double)8);
		LineData lineData6 = new LineData("圆环2",dataSeries6,Color.rgb(75, 166, 51));
		lineData6.setDotStyle(XEnum.DotStyle.RING2);//圆环第二种形状
		lineData6.getPlotLine().getDotPaint().setColor(Color.RED);
		lineData6.setLabelVisible(true);		
		//lineData6.getPlotLine().getPlotDot().setRingInnerColor(Color.GREEN);
		//lineData6.getPlotLine().getPlotDot().setRing2InnerColor(Color.GREEN);
		//lineData6.setLineStyle(XEnum.LineStyle.DASH);
		lineData6.getDotLabelPaint().setColor(Color.rgb(212, 64, 39));
		lineData6.getLabelOptions().getBox().getBackgroundPaint().setColor(Color.rgb(57, 172, 241));
		lineData6.getLabelOptions().getBox().setBorderLineColor(Color.YELLOW);
		*/
		
		chartData.add(lineData1);
		chartData.add(lineData2);
		chartData.add(lineData3);
		chartData.add(lineData4);
	//	chartData.add(lineData5);
	//	chartData.add(lineData6);
	}
	
	private void chartLabels()
	{
		labels.add("01s");
		labels.add("02s");
		labels.add("03s");
		labels.add("04s");
		labels.add("05s");
        labels.add("06s");
        labels.add("07s");
        labels.add("08s");
        labels.add("09s");
        labels.add("10s");
	}
	
	@Override
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub		
				
		if(event.getAction() == MotionEvent.ACTION_UP) 
		{			
			triggerClick(event.getX(),event.getY());
		}
		super.onTouchEvent(event);
		return true;
	}
	
	
	//触发监听
	private void triggerClick(float x,float y)
	{		
		
		//交叉线
		if(chart.getDyLineVisible())
		    chart.getDyLine().setCurrentXY(x,y);

		if(!chart.getListenItemClickStatus())
		{
			//交叉线
			if(chart.getDyLineVisible())
			    this.invalidate();
		}else{			
			PointPosition record = chart.getPositionRecord(x,y);			
			if( null == record)
			{
				if(chart.getDyLineVisible())
				    this.invalidate();
				return;
			}
	
			LineData lData = chartData.get(record.getDataID());
			Double lValue = lData.getLinePoint().get(record.getDataChildID());
		
			float r = record.getRadius();
			chart.showFocusPointF(record.getPosition(),r + r*0.5f);		
			chart.getFocusPaint().setStyle(Style.STROKE);
			chart.getFocusPaint().setStrokeWidth(3);		
			if(record.getDataID() >= 3)
			{
				chart.getFocusPaint().setColor(Color.BLUE);
			}else{
				chart.getFocusPaint().setColor(Color.RED);
			}		
			
			//在点击处显示tooltip
			mPaintTooltips.setColor(Color.RED);				
			//chart.getToolTip().setCurrentXY(x,y);
			chart.getToolTip().setCurrentXY(record.getPosition().x,record.getPosition().y); 
			
			chart.getToolTip().addToolTip(" Key:"+lData.getLineKey(),mPaintTooltips);
			chart.getToolTip().addToolTip(" Label:"+lData.getLabel(),mPaintTooltips);		
			chart.getToolTip().addToolTip(" Current Value:" +Double.toString(lValue),mPaintTooltips);
				
			
			//当前标签对应的其它点的值
			int cid = record.getDataChildID();
			String xLabels ;
			for(LineData data : chartData)
			{
				if(cid < data.getLinePoint().size())
				{
					xLabels = Double.toString(data.getLinePoint().get(cid));					
					chart.getToolTip().addToolTip("Line:"+data.getLabel()+","+ xLabels,mPaintTooltips);					
				}
			}
			this.invalidate();
		}
	}
}
