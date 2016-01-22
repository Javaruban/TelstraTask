package com.telstra.task;

        import android.test.ActivityInstrumentationTestCase2;
        import android.test.suitebuilder.annotation.SmallTest;
        import android.widget.ListView;
        import android.app.Activity;

/**
 * Created by Ruban on 1/22/2016.
 * Test case for ItemListView
 */


    public class ItemListTest extends ActivityInstrumentationTestCase2 {

        /** The activity containing ListView to be tested */
        ItemListActivity mActivity;

        /** The listview to be tested */
        ListView mListView;

        public ItemListTest() {
            super(ItemListActivity.class);
        }

        /** Setting up test fixture */
        @Override
        protected void setUp() throws Exception {
            super.setUp();

            /** Getting the reference to the activity containing listview to be tested */
            //mActivity = getActivity();

            /** Getting the reference to the activity to be tested */
            mListView = (ListView) mActivity.findViewById(R.id.itemListview);

        }

        @Override
        protected void tearDown() throws Exception {
            super.tearDown();
        }

        /** Testing Listview by checking its item count */
        @SmallTest
        public void testListView(){
            int expectedCount = 10;
            int actualCount = mListView.getAdapter().getCount();
            assertEquals(expectedCount, actualCount);
        }
    }

