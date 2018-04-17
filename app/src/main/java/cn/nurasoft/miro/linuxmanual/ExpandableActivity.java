package cn.nurasoft.miro.linuxmanual;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by miro on 12/02/17~.~
 **/

public class ExpandableActivity extends Activity {

    ImageButton _return;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commandview);

        _return=(ImageButton)findViewById(R.id._return);
        _return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClass(ExpandableActivity.this,MainActivity.class);
                ExpandableActivity.this.startActivity(intent);
            }
        });
        final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            //设置组视图的显示文字
            private String[] province = new String[] { "File Commands", "Process Management", "File Permission","SSH","Searching","System Info","Compression","Network","Installation","Shortcuts" };
            //子视图显示文字
            private String[][] city = new String[][] {
                    { "ls   :directory listing", "ls -al: formatted listing with hidden files", "cd DIR :change directory to DIR", "cd    :change to home", "pwd   :show current directory","mkdir DIR:create a directory DIR","rm FILE:delete FILE","rm -r DIR:delete directory DIR","rm -f FILE:force remove FILE","rm -rf DIR:force remove directory DIR",
                            "cp FILE1 FILE2:copy FILE1 to FILE2","cp -r DIR1 DIR2:copy directory"},
                    { "ps    :display your currently active processes", "top   :display all running processes", "kill  PID:kill process id PID", "killall PRO:kill processes named PROc*","bg    :lists stopped or background jobs;resume a stopped job in the background","fg     :brings the most recent job to foreground","fg N  :brings job N to the foreground"},
                    { "chmod OCTAL FILE:change the permissions of FILE to OCTAL", "which can be found separately for users,group,and world by adding:", "4:read(r)", "2:write(w)", "1:execute(x)","chmod 777:read write,execute for all","chmod 755:rwx for owner,rx for group and world." },
                    { "ssh USER@HOST:connect to HOST as USER", "ssh -p PORT USER@HOST:connect to HOST on port PORT as user", "ssh-copy-id USER@HOST:add your key to HOST for user "},
                    {"grep pattern files:search for pattern in files","grep -r pattern dir – search recursively for pattern in dir ",
                     "command | grep pattern – search for pattern in the output of command","locate file – find all instances of file"},
                    {"data : show the current date and time ","cal : show this month's calendar","uptime :show current uptime","w     :display who is online","whoami : who you are logged in as","finger user: display information about user","uname -a:show kernel information","cat /proc/cpuinfo :cpu information",
                    "cat /proc/meminfo : memory information","man command:show the manual for command","df   :show disk usage","du  :show directory space usage","free :show memory and swap usage",
                    "whereis app:show possible locations of app","which app:show which app will be run by default"},
                    {"tar cf file.tar files – create a tar named  file.tar containing files","tar xf file.tar:extract the files from file.tar","tar czf file.tar.gz files:create a tar with Gzip compression",
                    "tar xzf file.tar.gz: extract a tar using Gzip","tar cjf file.tar.bz2:create a tar with Bzip2","gzip file:compresses file and renames it to file.gz",
                    "tar cjf file.tar.bz2:extract a tar using Bzip2","gzip -d file.gz:decompresses file.gz back to file"},
                    {"ping host – ping host and output results","whois domain:get whois information for domain","dig domain:get DNS information for domain","dig -x host:reverse lookup host","wget file:download file","wget -c file:continue a stopped download"},
                    {"Install from source:",
                            "Examples:",
                            "make   " ,
                            "make install" ,
                            "dpkg -i pkg.deb – install a package (Debian)",
                            "rpm -Uvh pkg.rpm – install a package (RPM)"},
                    {"Ctrl+C – halts the current command",
                     "Ctrl+Z – stops the current command, resume with",
                     "fg     -in the foreground or bg in the background",
                     "Ctrl+D – log out of current session, similar to exit",
                     "Ctrl+W – erases one word in the current line",
                     "Ctrl+U – erases the whole line",
                     "Ctrl+R – type to bring up a recent command",
                     "!!     - repeats the last command",
                     "exit   – log out of current session"}
            };

            //自己定义一个获得文字信息的方法
            TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(ExpandableActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(0, 0, 0, 0);
                textView.setTextSize(16);
                textView.setTextColor(Color.BLACK);
                return textView;
            }


            //重写ExpandableListAdapter中的各个方法
            @Override
            public int getGroupCount() {
                return province.length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return province[groupPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return city[groupPosition].length;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return city[groupPosition][childPosition];
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                TextView textView = getTextView();
                textView.setTextColor(Color.BLACK);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);

                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                TextView textView = getTextView();
                SpannableStringBuilder builder=new SpannableStringBuilder(getChild(groupPosition, childPosition).toString());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(textView.getContext(), android.R.color.holo_purple));
                builder.setSpan(colorSpan,0,6, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                textView.setText(builder);
                textView.setTextColor(Color.parseColor("#123456"));
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean isChildSelectable(int groupPosition,int childPosition) {
                return true;
            }

        };

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.province);
        expandableListView.setAdapter(adapter);

        //设置item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        ExpandableActivity.this, adapter.getChild(groupPosition, childPosition).toString(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }


}
