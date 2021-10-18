FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
        file://fix-clang-driver-gcc-install-path-on-openembedded.patch \
        "
