package org.agetac.fragment;

import org.agetac.R;
import org.agetac.listener.IOnMenuEventListener;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class HiddenMenuFragment extends Fragment implements IMenuFragment, OnClickListener {

	private IOnMenuEventListener listener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu_hidden, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((ImageButton) getActivity().findViewById(R.id.btn_show_menu)).setOnClickListener(this);
	}
	
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_menu:
			if (listener != null) listener.onShowMenu();
			break;
		}
	}

	@Override
	public void setOnMenuEventListener(IOnMenuEventListener listener) {
		this.listener = listener;
	}

	@Override
	public void removeOnMenuEventListener() {
		this.listener = null;
	}
}
