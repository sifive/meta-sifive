require u-boot-common.inc
require u-boot.inc

DEPENDS += "bc-native dtc-native"

DEPENDS_append_freedom-u540 = " opensbi"

SRC_URI_append_freedom-u540 = " \
        file://sifive-fu540-compressed-booti-support.patch \
        file://mmc-boot.txt \
        "
