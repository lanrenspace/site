package com.lanrenspace.site.service.impl;

import com.lanrenspace.site.base.BaseBeanServiceImpl;
import com.lanrenspace.site.entity.Option;
import com.lanrenspace.site.repository.OptionRepository;
import com.lanrenspace.site.service.OptionService;
import com.lanrenspace.site.vo.OptionInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class OptionServiceImpl extends BaseBeanServiceImpl<Option, Long, OptionRepository> implements OptionService {

    /**
     * 获取所有类别信息
     *
     * @param request
     * @return
     */
    @Override
    public Flux<OptionInfoVO> getOptions(ServerRequest request) {
        return getEntityTemplate().select(Query.query(Criteria.where("deleteFlag").is(false)), Option.class).map(option -> {
            OptionInfoVO result = new OptionInfoVO();
            BeanUtils.copyProperties(option, result);
            return result;
        });
    }

    /**
     * 创建类别信息
     *
     * @param request
     * @return
     */
    @Override
    public Mono<Option> createOption(ServerRequest request) {
        return request.bodyToMono(Option.class).flatMap(this::save);
    }
}
