package bravo.launcher;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;

public class BravoAccessibilityService extends AccessibilityService {

    private long lastAttempt = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getPackageName() == null ||
                !"com.android.chrome".contentEquals(event.getPackageName())) {
            return;
        }

        // Evita vários cliques durante o carregamento do Chrome
        if (SystemClock.elapsedRealtime() - lastAttempt < 10000) {
            return;
        }

        lastAttempt = SystemClock.elapsedRealtime();

        // Aguarda o ScreenHub carregar e o botão aparecer
        handler.postDelayed(this::tocarBotaoFullscreen, 5000);
    }

    private void tocarBotaoFullscreen() {

        Path path = new Path();

        // Coordenada já testada na TV: 1280x720
        path.moveTo(640, 650);

        GestureDescription.StrokeDescription toque =
                new GestureDescription.StrokeDescription(
                        path,
                        0,
                        150
                );

        GestureDescription gesto =
                new GestureDescription.Builder()
                        .addStroke(toque)
                        .build();

        dispatchGesture(gesto, null, null);
    }

    @Override
    public void onInterrupt() {
    }
}
