package com.fonyou.finaltest.config;

import com.fonyou.root.utils.lib.classes.instance.ConfigWatcher;

public class CustomConfigWatcher extends ConfigWatcher
{
    public CustomConfigWatcher(String propertiesFilename, String charsetName)
    {
        super(propertiesFilename, charsetName);
    }

    public String getOrDefault(String key, String defValue)
    {
        final String value = super.get(key);
        return value != null ? value : defValue;
    }
}
