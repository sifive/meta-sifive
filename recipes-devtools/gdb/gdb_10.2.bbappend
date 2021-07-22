FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://0001-gdb-Support-DW_LLE_start_end.patch \
    "
