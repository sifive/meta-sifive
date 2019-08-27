FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Lock to one version of kernel
LINUX_VERSION = "5.2.9"
SRCREV = "v5.2.9"

# New WIC files adds new partition for FSBL thus we need to change rootfs
SRC_URI_append_freedom-u540 = " file://defconfig"

# TLB flush fixes
SRC_URI_append = " file://riscv-fix-flush_tlb_range-end-address-for-flush_tlb_page.patch \
                   file://v2-riscv-optimize-tlb-flush-path.patch"
