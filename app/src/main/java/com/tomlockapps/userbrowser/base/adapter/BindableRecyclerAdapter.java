package com.tomlockapps.userbrowser.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by walczewski on 04.03.2018.
 */

public abstract class BindableRecyclerAdapter<VH extends BindableRecyclerAdapter.ViewHolder, O, BC> extends RecyclerView.Adapter<VH> {

    private final List<O> items = new ArrayList<>();
    private final Class<VH> viewHolderClass;
    private final Class<BC> viewDataBindingClass;

    public BindableRecyclerAdapter() {
        this.viewHolderClass= (Class<VH>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.viewDataBindingClass = (Class<BC>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);

        try {
            VH vh = viewHolderClass.getDeclaredConstructor(getClass(), viewDataBindingClass).newInstance(this, binding);
            return vh;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Something went wrong with object ViewHolder creation. Did you forget to add the rule to proguard?");
    }

    public void onBindViewHolder(VH holder, int position) {
        O obj = getObjForPosition(position);
        holder.bindNow(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    protected O getObjForPosition(int position) {
        return items.get(position);
    }

    public void setItems(List<?> items) {
        this.items.clear();
        this.items.addAll((Collection<? extends O>) items);
    }

    protected abstract int getLayoutIdForPosition(int position);

    public static abstract class ViewHolder<VD extends ViewDataBinding, O> extends RecyclerView.ViewHolder {
        private final VD binding;

        public ViewHolder(VD binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        protected abstract void bind(VD binding, O object);

        void bindNow(O obj) {
            bind(binding, obj);
            binding.executePendingBindings();
        }
    }

}