inherit update-alternatives

ALTERNATIVE_PRIORITY = "10"
ALTERNATIVE_${PN}-doc = "history.3"
ALTERNATIVE_LINK_NAME[history.3] = "${mandir}/man3/history.3"
