package org.iosdevcamp.mermadia;

import com.badlogic.gdx.backends.iosmoe.IOSApplication;
import com.badlogic.gdx.backends.iosmoe.IOSApplicationConfiguration;
import com.intel.moe.natj.general.Pointer;
import org.iosdevcamp.mermadia.MermaniaGame;

import ios.foundation.NSAutoreleasePool;
import ios.uikit.c.UIKit;

public class IOSMoeLauncher extends IOSApplication.Delegate {

    protected IOSMoeLauncher(Pointer peer) {
        super(peer);
    }

    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        config.useAccelerometer = true;
        config.useCompass = true;
        return new IOSApplication(new MermaniaGame(), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = NSAutoreleasePool.alloc();
        UIKit.UIApplicationMain(0, null, null, IOSMoeLauncher.class.getName());
        pool.dealloc();
    }
}