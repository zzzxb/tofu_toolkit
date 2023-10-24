package cn.tofucat.toolkit.util;


public final class PathUtils {

    public static String formatForward(String path) {
        CheckParamUtils.isBlack(path).throwMessage("路径不能为空");

        String[] pathNames = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = pathNames.length - 1; i >= 0; i--) {
            String pathName = pathNames[i];
            if (StringUtils.isBlank(pathName)) {
                sb.insert(0, "/");
                continue;
            }
            if (pathName.equals("..")) {
                i -= 1;
                continue;
            }
            if (pathName.endsWith("..")) {
                continue;
            }
            sb.insert(0, pathName);
            sb.insert(0, i == 0 ? "" : "/");
        }
        return sb.toString();
    }
}
