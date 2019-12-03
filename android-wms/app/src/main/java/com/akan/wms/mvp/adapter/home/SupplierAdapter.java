package com.akan.wms.mvp.adapter.home;

        import android.content.Context;
        import android.support.annotation.LayoutRes;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.akan.wms.R;
        import com.akan.wms.bean.SupplierBean;
        import com.jude.easyrecyclerview.adapter.BaseViewHolder;
        import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

        import java.util.List;

public class SupplierAdapter extends RecyclerArrayAdapter<SupplierBean> {
    public SupplierAdapter(Context context, List<SupplierBean> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new SupplierAdapter.ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<SupplierBean> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_supplier);
            tvName = $(R.id.tvName);
        }

        @Override
        public void setData(SupplierBean data) {
            super.setData(data);
            tvName.setText((getDataPosition() + 1) + "." + data.getName() + "（" + data.getCode() + "）");
        }
    }

}
