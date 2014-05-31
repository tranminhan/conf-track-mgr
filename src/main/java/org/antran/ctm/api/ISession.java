package org.antran.ctm.api;

import java.util.Date;

public interface ISession extends Iterable<ITalk> {

	ITalkDetail[] getTalkDetails();

	Date endTime();
}
