# TabView
TabView is used to change different pages in android app，such as personal chat and group chat or more pages，now we can use it in our apps. 
# Usage
        mTabView = (TabView) findViewById(R.id.tabview1);
        List<String> textlist = new ArrayList<String>();
        textlist.add("消息");
        textlist.add("圈子");
        mTabView.setTextSize(13)
                .setColor(Color.GRAY, getResources().getColor(R.color.colorPrimary))
                .setTextColor(Color.BLACK, Color.WHITE)
                .setStrokeColor(Color.GREEN, Color.RED)
                .setCornerRadius(10)
                .setStrokeWidth(1)
                .setDashWidth(0)
                .setDashGap(0)
                .setTexts(textlist)
                .setSelected(1)
                .show();
        mTabView.setOnTabItemClickListener(new TabView.OnTabItemClickListener() {

            @Override
            public void onTabItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "position：" + position, Toast.LENGTH_SHORT).show();
            }
        });
# Result
# License

    Copyright 2017 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
