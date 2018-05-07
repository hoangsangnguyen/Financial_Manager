package com.example.nhattruong.financialmanager.mvp.jar_detail;

import com.example.nhattruong.financialmanager.base.BasePresenter;
import com.example.nhattruong.financialmanager.base.IBaseView;
import com.example.nhattruong.financialmanager.model.TabHeader;
import com.example.nhattruong.financialmanager.utils.DateUtils;

import java.util.Date;
import java.util.List;

import butterknife.Unbinder;

public class JarDetailPresenter extends BasePresenter implements JarDetailContract.Presenter {

    private List<TabHeader> mListTabHeader;
    private Date dateStart, dateEnd;

    public List<TabHeader> getListTabHeader() {
        return mListTabHeader;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public JarDetailContract.View getView() {
        return (JarDetailContract.View)super.getView();
    }

    @Override
    public void onCreate(IBaseView view, Unbinder binder) {
        super.onCreate(view, binder);
        mListTabHeader = getResourcesManager().getListTabHeaderJarDetail();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
