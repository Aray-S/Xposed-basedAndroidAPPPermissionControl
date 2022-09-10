package com.app.example.project;

import java.util.ArrayList;
import java.util.List;

public class APKInfo implements Comparable<APKInfo> {
    public String apk_name;
    public String package_name;
    public int flags;
    public List<PermissionSetting> permissionsSettings = new ArrayList<PermissionSetting>();

    public APKInfo() {
        super();
        apk_name = "";
        package_name = "";
    }

    @Override
    public int compareTo(APKInfo o) {
        int i = this.apk_name.compareTo(o.apk_name);
        if (i == 0) { return this.package_name.compareTo(o.package_name); }
        return i;
    }
}
