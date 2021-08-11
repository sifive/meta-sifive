FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:riscv64 = " \
        file://fix-clang-driver-gcc-install-path-on-openembedded.patch \
        "
