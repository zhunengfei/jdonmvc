package com.jdon.mvc.scan;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public interface Filter {

    boolean accepts(String filename);
}
