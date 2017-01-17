[![Wercker](https://api.travis-ci.org/karthyks/Runtime-Permissions.svg?branch=master)](https://travis-ci.org/karthyks/Runtime-Permissions) 
[![Wercker](https://img.shields.io/badge/jcenter-v1.4-yellow.svg)](https://bintray.com/karthik-logs/karthyks/Runtime-Permissions/1.4)
[![Wercker](https://img.shields.io/badge/Android--Arsenal-Runtime--Permissions-brightgreen.svg)](https://android-arsenal.com/details/1/4522)

# Runtime-Permissions

# Usage 

To ask the permission from the user, use the following code. 

```java
Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_LOCATION)
        .usingActivity(AppCompatActivity).withRationale("Some rationale message!")
        .build();
permission.requestPermission(REQUEST_CODE);
```

or

```java
Permission permission = new Permission.PermissionBuilder(Permission.REQUEST_LOCATION)
        .usingFragment(Fragment).withRationale("Some rationale message!")
        .build();
permission.requestPermission(REQUEST_CODE);
```

The user's response to the permission request will be reflected onActivityResult, as shown below.

```java
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE) {
      switch (resultCode) {
        case PermissionActivity.PERMISSION_GRANTED:
          Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
          break;
        case PermissionActivity.PERMISSION_DENIED:
          Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
          break;
        case PermissionActivity.PERMISSION_PERMANENTLY_DENIED:
          Toast.makeText(this, "Permanently denied", Toast.LENGTH_SHORT).show();
          PermissionUtil.openAppSettings(this);
          break;
        default:
      }
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
}
```
#Note
If it throws "Permission Denied" even after allowing it, check whether all the permission is added in the manifest for the permission group.
For example, for location permission add both COARSE and FINE_LOCATION permission in the manifest.
