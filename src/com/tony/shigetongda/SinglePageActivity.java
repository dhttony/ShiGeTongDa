package com.tony.shigetongda;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SinglePageActivity extends Activity implements OnTouchListener {

	private TextView contentTextView;
	private ImageView backButtonImageView;
	private ImageView bannerImageView;
	private RelativeLayout arrowLeftRelativeLayout;
	private RelativeLayout arrowRightRelativeLayout;
	private TextView arrowLeftTextView;
	private TextView arrowRightTextView;
	private ImageView arrowLeftImageView;
	private ImageView arrowRightImageView;
	private TextView activityTitleTextView;

	private int pageIndex = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_page);
		initViews();
		pageIndex = getIntent().getExtras().getInt(Common.PAGE_INDEX);
		updateViewByIndex(pageIndex);
	}

	private void initViews() {
		contentTextView = (TextView) findViewById(R.id.content);
		contentTextView.setMovementMethod(ScrollingMovementMethod.getInstance());

		backButtonImageView = (ImageView) findViewById(R.id.back);
		backButtonImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		bannerImageView = (ImageView) findViewById(R.id.banner);

		arrowLeftRelativeLayout = (RelativeLayout) findViewById(R.id.arrow_left);
		arrowLeftRelativeLayout.setOnTouchListener(this);

		arrowRightRelativeLayout = (RelativeLayout) findViewById(R.id.arrow_right);
		arrowRightRelativeLayout.setOnTouchListener(this);

		arrowLeftTextView = (TextView) findViewById(R.id.arrow_left_text);

		arrowRightTextView = (TextView) findViewById(R.id.arrow_right_text);

		arrowLeftImageView = (ImageView) findViewById(R.id.arrow_left_arrow);

		arrowRightImageView = (ImageView) findViewById(R.id.arrow_right_arrow);

		activityTitleTextView = (TextView) findViewById(R.id.activity_title);
	}

	private void updateViewByIndex(int pageIndex) {
		int bannerBackgroundResourceID = R.drawable.gongsijianjie_background;
		CharSequence content = "";
		CharSequence arrowLeftText = "";
		CharSequence arrowRightText = "";
		CharSequence activityTitle = "";

		switch (pageIndex) {
		case Common.INDEX_GONG_SI_JIAN_JIE:
			activityTitle = "公司简介";
			bannerBackgroundResourceID = R.drawable.gongsijianjie_background;
			content = "\t\t福州市世格通达财富管理有限公司旨在为全国各地期货、股票体验交流中心的合作发展提供资金和资源支持、投资者教育、工作人员培训等相关服务。";
			arrowLeftText = "";
			arrowRightText = "招商简介";
			break;
		case Common.INDEX_ZHAO_SHANG_JIAN_JIE:
			activityTitle = "招商简介";
			bannerBackgroundResourceID = R.drawable.zhaoshangjianjie_background;
			content = "\t\t随着金融机构人民币贷款和存款基准利率持续下调，以及房地产市场长期低迷，大量资金涌入股票、股指期货市场，致使股指屡创新高，成交量大幅提升，特别是股指期货的做空机制，T+0交易模式，保证金制度（以小博大）吸引了大量投资者参于，趁着利好的东风和高额的利润回报股指期货俱乐部（股指、股票交流体验中心）兴起。\n\n\t\t股指期货体验交流中心是一种新的金融投资模式，是以股指期货为投资对象，以合作、代理为前提，以民间借贷和会员信用为基础，不占用客户保证金即可进行股指期货交易的一种新模式。\n\n\t\t股指期货全称为股票价格指数期货，是指以股价指数为标的物的标准化期货合约。双方约定在未来的某个特定日期，按照事先确定的股价指数的大小，采用现金交割的方式，进行标的指数的买卖。";
			arrowLeftText = "公司简介";
			arrowRightText = "合作模式";
			break;
		case Common.INDEX_HE_ZUO_MO_SHI:
			activityTitle = "合作模式";
			bannerBackgroundResourceID = R.drawable.hezuomoshi_background;
			content = " \t\t此处需要沟通后填入相应内容";
			arrowLeftText = "招商简介";
			arrowRightText = "合作者利润点";
			break;
		case Common.INDEX_HE_ZUO_ZHE_LI_RUN_DIAN:
			activityTitle = "合作者利润点";
			bannerBackgroundResourceID = R.drawable.hezuozhelirundian_background;
			content = "\t\t投资者利润：一个股指交流体验中心（股指俱乐部）日均5名体验交易客户，一个客户日均体验交易3次，一天15次，15次Х300元/手=4500元，每月22个交易日，总计为9900元，根据实际情况一般一个交流体验中心（股指俱乐部）日均收入在一万左右。";
			arrowLeftText = "合作模式";
			arrowRightText = "我要加盟";
			break;
		case Common.INDEX_WO_YAO_JIA_MENG:
			activityTitle = "我要加盟";
			bannerBackgroundResourceID = R.drawable.woyaojiameng_background;
			content = "\t\t我能做吗？我有风险吗？请联系我们\n\t\t\t\t1、我不懂金融，不会炒股票、期货，可以做吗？\n\t\t\t\t2、我需要投入多少资金？\n\t\t\t\t3、有哪些风险需要注意？\n\n\t\t合作、加盟我们需要做什么？\n\t\t\t\t1、营业场所\n\t\t\t\t2、环境舒适面积≦ 200平方，提供茶水零食水果，中餐\n\t\t\t\t3、成本价硬件采购，硬件配置，场地布置（电视电脑网络布置）每层：一体机两台（一台下单一台记录），60寸电视配套  苹果mini电脑（看行情），打印机，路由器，插板， 网线。（温馨提示牌 借款合同 结算单 日报表）\n\t\t\t\t4、准备部分自有周转资金（一个普通规模的股指交流体验中心（股指俱乐部）需要占用200-500万的资金，合作、加盟我们只需提供10%的资金作为保证金，以及支付每月1.4%的资金费用）。";
			arrowLeftText = "合作者利润点";
			arrowRightText = "";
			break;
		default:
			break;
		}

		bannerImageView.setImageResource(bannerBackgroundResourceID);
		contentTextView.setText(content);
		contentTextView.setScrollX(0);
		contentTextView.setScrollY(0);
		if (arrowLeftText.equals("")) {
			arrowLeftRelativeLayout.setVisibility(View.INVISIBLE);
		} else {
			arrowLeftRelativeLayout.setVisibility(View.VISIBLE);
			arrowLeftTextView.setText(arrowLeftText);
		}
		if (arrowRightText.equals("")) {
			arrowRightRelativeLayout.setVisibility(View.INVISIBLE);
		} else {
			arrowRightRelativeLayout.setVisibility(View.VISIBLE);
			arrowRightTextView.setText(arrowRightText);
		}
		activityTitleTextView.setText(activityTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("tony", "SinglePageActivity onTouch v = " + v + ", event = " + event.getAction());
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (v == arrowLeftRelativeLayout) {
				arrowLeftImageView.getDrawable().setAlpha(100);
			} else if (v == arrowRightRelativeLayout) {
				arrowRightImageView.getDrawable().setAlpha(100);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (v == arrowLeftRelativeLayout) {
				arrowLeftImageView.getDrawable().setAlpha(255);
				updateViewByIndex(pageIndex - 1);
				pageIndex--;
			} else if (v == arrowRightRelativeLayout) {
				arrowRightImageView.getDrawable().setAlpha(255);
				updateViewByIndex(pageIndex + 1);
				pageIndex++;
			}
			break;
		default:
			break;
		}
		return true;
	}
}
