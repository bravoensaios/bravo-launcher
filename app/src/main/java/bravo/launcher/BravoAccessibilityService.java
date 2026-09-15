package bravo.launcher;

import android.accessibilityservice.AccessibilityService;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

public class BravoAccessibilityService extends AccessibilityService {
    private long lastClick = 0;

    @Override public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getPackageName() == null || !"com.android.chrome".contentEquals(event.getPackageName())) return;
        if (SystemClock.elapsedRealtime() - lastClick < 5000) return;

        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) return;
        List<AccessibilityNodeInfo> nodes = root.findAccessibilityNodeInfosByText("ABRIR SCREENHUB");
        if (nodes == null) return;

        for (AccessibilityNodeInfo node : nodes) {
            AccessibilityNodeInfo target = node;
            while (target != null && !target.isClickable()) target = target.getParent();
            if (target != null && target.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                lastClick = SystemClock.elapsedRealtime();
                return;
            }
        }
    }

    @Override public void onInterrupt() { }
}
