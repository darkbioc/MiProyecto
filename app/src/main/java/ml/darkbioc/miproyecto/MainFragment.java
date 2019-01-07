package ml.darkbioc.miproyecto;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment
{

	GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);

	public MainFragment()
	{
		// Required empty public constructor
	}


	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment

		return inflater.inflate(R.layout.fragment_blank, container, false);
	}

	@Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);

		layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				// 5 is the sum of items in one repeated section
				switch (position % 5) {
					// first two items span 3 columns each
					case 0:
						return 2;
					case 1:
						// next 3 items span 2 columns each
					case 2:
					case 3:
					case 4:
						return 1;
				}
				throw new IllegalStateException("internal error");
			}
		});

		ArrayList<MainItem> items = new ArrayList<>();
		items.add(new MainItem(R.string.ItemTest, R.drawable.sample));
		items.add(new MainItem(R.string.ItemTest2, R.drawable.sample2));
		items.add(new MainItem(R.string.ItemTest3, R.drawable.sample3));

		final RecyclerView recView = getView().findViewById(R.id.recyclerView);

		final MainItemAdapter adaptador = new MainItemAdapter(items);
		recView.setAdapter(adaptador);
		recView.setLayoutManager(layoutManager);
	}




	/*@Override public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);


	}*/
}
