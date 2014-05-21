package com.spottechindustrial.carpool.android.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import android.util.Log;

public class HandlerThreadPoolManager {
    private static final String TAG = HandlerThreadPoolManager.class.getSimpleName();

    private static final int CORE_THREAD_NUMBER = 3;
    private static final int EXTRA_THREAD_NUMBER = 2;
    private static final int MAX_QUEUE_SIZE = 500;

    private static volatile int mUserSetSize;

    private static volatile HandlerThreadPoolManager instance;

    private static volatile CoreHandlerThread[] mCoreThreads;
    private static volatile ExtraHandlerThread[] mExtraThreads;

    private static volatile ArrayBlockingQueue<Runnable> mRunnableQueue;
    private static volatile List<Runnable> mRunnableList;

    private HandlerThreadPoolManager() {}

    public static HandlerThreadPoolManager getInstance() {
        if (null == instance) {
            synchronized (HandlerThreadPoolManager.class) {
                if (null == instance) {
                    instance = new HandlerThreadPoolManager();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private void init() {
        mUserSetSize = MAX_QUEUE_SIZE;

        mRunnableQueue = new ArrayBlockingQueue<Runnable>(MAX_QUEUE_SIZE);
        mRunnableList = new LinkedList<Runnable>();

        mCoreThreads = new CoreHandlerThread[CORE_THREAD_NUMBER];
        mExtraThreads = new ExtraHandlerThread[EXTRA_THREAD_NUMBER];

        for (int i = 0; i < CORE_THREAD_NUMBER; i++) {
            mCoreThreads[i] = new CoreHandlerThread(i);
        }
    }

    private static Runnable getRunnableFromHead() {
        Runnable runnable = null;
        if (mRunnableList.size() > 0) {
            synchronized (HandlerThreadPoolManager.class) {
                if (mRunnableList.size() > 0) {
                    runnable = mRunnableList.remove(0);
                }
            }
        } else {
            if (mRunnableQueue.size() > 0) {
                synchronized (HandlerThreadPoolManager.class) {
                    if (mRunnableQueue.size() > 0) {
                        runnable = mRunnableQueue.poll();
                    }
                }
            }
        }
        return runnable;
    }

    protected void postToThread(final AbstractHandlerThread thread) {
        final Runnable runnable = getRunnableFromHead();
        if (null != runnable) {
            thread.mIdleFlag = false;
            thread.mHandler.post(runnable);
        } else {
            thread.mIdleFlag = true;
        }
    }

    protected void closeIdleExtraThread(final int index) {
        final Runnable closingRunnable = new Runnable(){
            @Override
            public void run() {
                if (mExtraThreads[index] != null && mExtraThreads[index].mIdleFlag) {
                    Log.v(TAG, mExtraThreads[index].getThreadName() + " is now closing.");
                    mExtraThreads[index] = null;
                }
            }
        };
        boolean posted = false;
        for (int i = 0; i < CORE_THREAD_NUMBER; i++) {
            if (mCoreThreads[i].mIdleFlag) {
                Log.v(TAG, mCoreThreads[i].getThreadName() + " will be used to close " + mExtraThreads[index].getThreadName() + ".");
                mCoreThreads[i].mHandler.post(closingRunnable);
                posted = true;
                break;
            }
        }
        if (!posted) {
            final Runnable runnable = getRunnableFromHead();
            if (null != runnable) {
                mExtraThreads[index].mHandler.post(runnable);
            } else {
                mCoreThreads[0].mHandler.post(closingRunnable);
            }
        }
    }

    public void setRunnableQueueSize(final int userSetSize) {
        mUserSetSize = userSetSize;
    }

    public void submitToFront(final Runnable runnable) {
        synchronized(this) {
            mRunnableList.add(runnable);
        }
        dispatchRunnables();
    }

    public void submitToBack(final Runnable runnable) {
        synchronized(this) {
            if (mRunnableQueue.size() >= mUserSetSize) {
                mRunnableQueue.remove();
            }
            mRunnableQueue.add(runnable);
        }
        dispatchRunnables();
    }

    private void dispatchRunnables() {
        for (int i = 0; i < CORE_THREAD_NUMBER; i++) {
            if (mCoreThreads[i].mIdleFlag) {
                this.postToThread(mCoreThreads[i]);
                return;
            }
        }
        for (int i = 0; i < EXTRA_THREAD_NUMBER; i++) {
            if (null == mExtraThreads[i]) {
                synchronized (this) {
                    if (null == mExtraThreads[i]) {
                        final Runnable runnable = getRunnableFromHead();
                        if (null != runnable) {
                            mExtraThreads[i] = new ExtraHandlerThread(i);
                            Log.v(TAG, mExtraThreads[i].getName() + " has been created.");

                            mExtraThreads[i].mIdleFlag = false;
                            mExtraThreads[i].mHandler.post(runnable);
                        }
                        return;
                    }
                }
            }
        }
    }
}
