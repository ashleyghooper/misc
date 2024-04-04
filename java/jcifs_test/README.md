# JCIFS SMB testing

Included are simple Java programs to test 3 connecting to Windows SMB shares
using 3 different variants of the `jcifs` open source Java library for
SMB file share connectivity.

## Building

`mvn package`

## Testing

`java -jar ${jar_file} DOMAIN USERNAME SMB_URL`

### Example

```sh
java -jar jcifs.jcifs_test-1.0.0-shaded.jar mydomain myuser smb://myserver/myshare/
```
