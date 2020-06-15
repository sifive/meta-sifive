require u-boot-common.inc
require u-boot.inc

DEPENDS += "bc-native dtc-native"

SRC_URI_append_freedom-u540 = " \
        file://mmc-boot.txt \
        "
