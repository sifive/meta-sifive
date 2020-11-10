FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_riscv64 = " \
        file://fix-clang-driver-gcc-install-path-on-openembedded.patch \
        "
