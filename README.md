# CommonListActivity
公共的列表－共用一个adapter

很多时候一些列表样式是一样，写几遍会比较麻烦，是可以共用的，这就需要写一个公共的adapter

两种方式

1.设置一种公共的类，来存放数据，不同种类的类转换成公共的类

public class CommonAdapter extends BaseAdapter {

    private Context mContext;
    private List<CommonData> mDatas = new ArrayList();

    public CommonAdapter(Context context, List<CommonData> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CommonData getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_common, null);
            viewHolder.desc = (TextView) view.findViewById(R.id.desc);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        CommonData data = getItem(i);
        viewHolder.desc.setText(data.desc);
        return view;
    }

    class ViewHolder {
        TextView desc;
    }
}

设置数据的时候最后都是List<CommonData>, 如果不是的，可以自行转换。

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

2.adpter中的具体类用泛型Ｔ来代替，具体的赋值写成抽像方法，实例化adapter的时候实现具体的操作

public abstract class CommonAdapter2<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mDatas = new ArrayList();

    public CommonAdapter2(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_common, null);
            viewHolder.desc = (TextView) view.findViewById(R.id.desc);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.desc.setText(setDesc(getItem(i)));
        return view;
    }

    class ViewHolder {
        TextView desc;
    }

    public abstract String setDesc(T data);
}

这种方式的在使用的时候可以兼容多不周的数据类

例如：

CommonAdapter2 mAdapter = new CommonAdapter2<CommonData>(this, getCommonDatas()) {
            @Override
            public String setDesc(CommonData data) {
                return data.desc;
            }
        };

CommonAdapter2 mAdapter2 = new CommonAdapter2<BookData>(this, getBookDatas()) {
            @Override
            public String setDesc(BookData data) {
                return data.bookName;
            }
        };
        
 这样即可以适用不同的类，重写抽象方法的时候即可以赋值操作。
