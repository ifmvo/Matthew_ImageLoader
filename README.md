# Matthew_ImageLoader

### 简单使用
```
ILFactory.getLoader().load(this, imageview, url, LoaderOptions().defaultOptions())
```

### 添加展位图和加载错误展示图
```
ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round))
```

### 跳过缓存
```
ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache())
```

### 图片圆形展示
```
ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache().circle())
```

### 监听器的使用（ 成功、失败、加载进度 ）
```
ILFactory.getLoader().load(this, imageview, url, LoaderOptions().placeHolder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).skipCache().circle(), object : LoadListener(){

    override fun onLoadCompleted(drawable: Drawable?): Boolean {
        return false
    }

    override fun onLoadFailed(e: Throwable?): Boolean {
        return super.onLoadFailed(e)
    }

    override fun onLoadProgress(progress: Int) {
        super.onLoadProgress(progress)
    }
    
})
```