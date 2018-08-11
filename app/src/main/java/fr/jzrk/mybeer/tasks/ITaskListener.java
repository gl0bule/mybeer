package fr.jzrk.mybeer.tasks;

import android.os.AsyncTask;

public interface ITaskListener<T> {
    void onTaskFinished(AsyncTask<?, ?, T> task);

}
