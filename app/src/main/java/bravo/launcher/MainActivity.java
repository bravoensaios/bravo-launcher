package bravo.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String URL = "https://bravoensaios.github.io/screenhub-bravo/";

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(48, 48, 48, 48);
        root.setBackgroundColor(Color.BLACK);

        TextView title = new TextView(this);
        title.setText("BRAVO LAUNCHER\n\nTeste 1");
        title.setTextColor(Color.WHITE);
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);

        Button accessibility = new Button(this);
        accessibility.setText("1. ATIVAR ACESSIBILIDADE");
        accessibility.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));

        Button open = new Button(this);
        open.setText("2. ABRIR SCREENHUB NO CHROME");
        open.setOnClickListener(v -> openChrome());

        root.addView(title, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(accessibility, params());
        root.addView(open, params());
        setContentView(root);
    }

    private LinearLayout.LayoutParams params() {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 30, 0, 0);
        return p;
    }

    private void openChrome() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        i.setComponent(new ComponentName("com.android.chrome", "com.google.android.apps.chrome.Main"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try { startActivity(i); }
        catch (Exception e) {
            Intent fallback = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
            fallback.setPackage("com.android.chrome");
            startActivity(fallback);
        }
    }
}
