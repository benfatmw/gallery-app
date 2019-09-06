package com.lin.mobile.gallery.ui.injection;

import android.app.Application;
import com.lin.mobile.gallery.ui.GalleryApplication;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(
        modules = {BuildersBindingModule.class,
                AndroidSupportInjectionModule.class,
                ApplicationModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        ApplicationComponent build();

        @BindsInstance
        Builder application(Application application);
    }

    void inject(GalleryApplication app);
}
